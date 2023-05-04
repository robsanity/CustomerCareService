package it.polito.wa2.g35.server.ticketing.ticket

import com.fasterxml.jackson.databind.ObjectMapper
import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.products.toDTO
import it.polito.wa2.g35.server.profiles.customer.Customer
import it.polito.wa2.g35.server.profiles.customer.CustomerRepository
import it.polito.wa2.g35.server.profiles.customer.CustomerService
import it.polito.wa2.g35.server.profiles.customer.toDTO
import it.polito.wa2.g35.server.profiles.employee.expert.Expert
import it.polito.wa2.g35.server.profiles.employee.expert.ExpertRepository
import it.polito.wa2.g35.server.profiles.employee.expert.ExpertService
import it.polito.wa2.g35.server.profiles.employee.expert.toDTO
import it.polito.wa2.g35.server.ticketing.order.Order
import it.polito.wa2.g35.server.ticketing.order.OrderInputDTO
import it.polito.wa2.g35.server.ticketing.order.OrderService
import it.polito.wa2.g35.server.products.ProductDTO
import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.profiles.customer.CustomerDTO
import it.polito.wa2.g35.server.profiles.customer.CustomerService
import it.polito.wa2.g35.server.profiles.employee.expert.ExpertDTO
import it.polito.wa2.g35.server.profiles.employee.expert.ExpertService
import it.polito.wa2.g35.server.ticketing.order.OrderInputDTO
import it.polito.wa2.g35.server.ticketing.order.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.*

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers

class TicketControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var ticketService: TicketService

    @Autowired
    lateinit var ticketRepository: TicketRepository

    @Autowired
    lateinit var expertService: ExpertService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var orderService: OrderService



    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var expertService: ExpertService

    @Autowired
    lateinit var orderService: OrderService



    companion object {
        @Container
        val postgres = PostgreSQLContainer("postgres:latest")

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgres::getJdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
            registry.add("spring.jpa.hibernate.ddl-auto") { "create-drop" }
        }
    }

    @Test
    fun `Empty Ticket list`() {
        // Given
        ticketRepository.deleteAll()

        // When
        val result = mockMvc
            .perform(
                MockMvcRequestBuilders.get("/API/tickets")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // Then
        assertEquals("[]", result.response.contentAsString)
    }

    @Test
    fun `Insert one ticket and get it`() {
        // Given
        ticketRepository.deleteAll()
        val expert = expertService.createExpert(ExpertDTO("exp123", "name", "surname", "expert@example.com", "automotive"))
        val product = productService.createProduct(
            ProductDTO(
                "1234abc",
                "Example Product"
            )
        )
        val customer = customerService.createCustomer(
            CustomerDTO(
                "customer@example.com",
                "customer name",
                "customer surname"
            )
        )
        orderService.createOrder(
            OrderInputDTO(
                null,
                customer!!.email,
                product!!.id,
                Date(),
                Date(Date().time + 2 * 60 * 60 * 1000) //Now + 2 hours
            )
        )
        val ticket = ticketService.createTicket(
            TicketInputDTO(
                null,
                null,
                "description",
                null,
                TicketStatusValues.OPEN.name,
                expert!!.id,
                product.id,
                customer.email
            )
        )

        // When
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/API/tickets/${ticket?.id}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .perform(
                MockMvcRequestBuilders.get("/API/tickets")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val content = result.response.contentAsString
        val tickets = ObjectMapper().readValue(content, Array<TicketDTO>::class.java)
        assertEquals(3, tickets.size)

        // Then
        val returnedTicket = objectMapper.readValue(result.response.contentAsString, TicketDTO::class.java)
        assertEquals(ticket?.id, returnedTicket.id)
        assertEquals(ticket?.creationTimestamp, returnedTicket.creationTimestamp)
        assertEquals(ticket?.issueDescription, returnedTicket.issueDescription)
        assertEquals(ticket?.status, returnedTicket.status)
        assertEquals(ticket?.expert?.id, returnedTicket.expert?.id)
        assertEquals(ticket?.product?.id, returnedTicket.product.id)
        assertEquals(ticket?.customer?.email, returnedTicket.customer.email)
        assertEquals(ticket?.priority, returnedTicket.priority)
    }


}
