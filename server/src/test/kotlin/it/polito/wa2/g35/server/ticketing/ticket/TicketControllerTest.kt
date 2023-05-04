package it.polito.wa2.g35.server.ticketing.ticket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import it.polito.wa2.g35.server.products.*
import it.polito.wa2.g35.server.profiles.customer.*
import it.polito.wa2.g35.server.profiles.employee.expert.*
import it.polito.wa2.g35.server.ticketing.order.OrderInputDTO
import it.polito.wa2.g35.server.ticketing.order.OrderRepository
import it.polito.wa2.g35.server.ticketing.order.OrderService
import it.polito.wa2.g35.server.ticketing.ticketStatus.TicketStatus
import it.polito.wa2.g35.server.ticketing.ticketStatus.TicketStatusRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.event.annotation.BeforeTestExecution
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
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
    lateinit var expertRepository: ExpertRepository

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var ticketStatusRepository: TicketStatusRepository


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

    @BeforeEach
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    fun beforeEach() {
        ticketStatusRepository.deleteAll()
        ticketRepository.deleteAll()
        orderRepository.deleteAll()
        expertRepository.deleteAll()
        productRepository.deleteAll()
        customerRepository.deleteAll()
        val expert1 = Expert("1", "Mimmo", "Lello", "1@example.it", "Fiche strette")
        val product1 = Product("1", "Product 1")
        val customer1 = Customer("prova@example.it", "Franco", "Galati")
        val order1 = OrderInputDTO(null, "prova@example.it", "1", Date(), Date(Date().time + 2 * 60 * 60 * 1000))
        expertService.createExpert(expert1.toDTO())
        productService.createProduct(product1.toDTO())
        customerService.createCustomer(customer1.toDTO())
        orderService.createOrder(order1)
    }

    @Test
    fun testGetEmptyTicketList() {
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
    fun testThreeTicketList() {
        val ticket1 = TicketInputDTO(
            null,
            null,
            "description 1",
            TicketPriority.LOW.name,
            TicketStatusValues.OPEN.name,
            "1",
            "1",
            "prova@example.it"
        )
        val ticket2 = TicketInputDTO(
            null,
            null,
            "description 2",
            TicketPriority.MEDIUM.name,
            TicketStatusValues.OPEN.name,
            "1",
            "1",
            "prova@example.it"
        )
        val ticket3 = TicketInputDTO(
            null,
            null,
            "description 3",
            TicketPriority.HIGH.name,
            TicketStatusValues.CLOSED.name,
            "1",
            "1",
            "prova@example.it"
        )
        ticketService.createTicket(ticket1)
        ticketService.createTicket(ticket2)
        ticketService.createTicket(ticket3)

        // When
        val result = mockMvc
            .perform(
                MockMvcRequestBuilders.get("/API/tickets")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val content = result.response.contentAsString
        val tickets = ObjectMapper().readValue(content, Array<TicketDTO>::class.java)
        assertEquals(3, tickets.size)

        // Then
    }

    @Test
    fun testGetTicketById() {
        // Given
        val ticket = ticketService.createTicket(
            TicketInputDTO(
                null,
                null,
                "description",
                null,
                TicketStatusValues.OPEN.name,
                "1",
                "1",
                "prova@example.it"
            )
        )

        // When
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/API/tickets/${ticket?.id}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

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

    @Test
    fun testPostTicket() {
        val ticket = TicketInputDTO(
            null,
            null,
            "description",
            null,
            TicketStatusValues.OPEN.name,
            "1",
            "1",
            "prova@example.it"
        )

        val requestBody = objectMapper.writeValueAsString(ticket)

        // When
        val result = mockMvc.perform(
            MockMvcRequestBuilders
                .post("/API/tickets/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andExpect(MockMvcResultMatchers.status().isCreated)
            .andReturn()

        val insertedTicket = objectMapper.readValue(result.response.contentAsString, TicketDTO::class.java)

        // Then
/*val returnedTicket = objectMapper.readValue(result.response.contentAsString, TicketDTO::class.java)
        assertEquals(ticket?.id, returnedTicket.id)
        assertEquals(ticket?.creationTimestamp, returnedTicket.creationTimestamp)
        assertEquals(ticket?.issueDescription, returnedTicket.issueDescription)
        assertEquals(ticket?.status, returnedTicket.status)
        assertEquals(ticket?.expert?.id, returnedTicket.expert?.id)
        assertEquals(ticket?.product?.id, returnedTicket.product.id)
        assertEquals(ticket?.customer?.email, returnedTicket.customer.email)
        assertEquals(ticket?.priority, returnedTicket.priority)*/
    }

}
