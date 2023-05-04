package it.polito.wa2.g35.server.ticketing.message

import com.fasterxml.jackson.databind.ObjectMapper
import it.polito.wa2.g35.server.products.*
import it.polito.wa2.g35.server.profiles.customer.*
import it.polito.wa2.g35.server.profiles.employee.expert.*
import it.polito.wa2.g35.server.ticketing.order.OrderInputDTO
import it.polito.wa2.g35.server.ticketing.order.OrderRepository
import it.polito.wa2.g35.server.ticketing.order.OrderService
import it.polito.wa2.g35.server.ticketing.ticket.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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
import java.awt.PageAttributes
import java.text.SimpleDateFormat

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class MessageControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var messageService: MessageService

    @Autowired
    lateinit var messageRepository: MessageRepository

    @Autowired
    lateinit var ticketRepository: TicketRepository

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var expertService: ExpertService

    @Autowired
    lateinit var ticketService: TicketService

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var expertRepository: ExpertRepository

    @Autowired
    lateinit var orderRepository: OrderRepository

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

    @BeforeEach
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    fun beforeEach() {
        messageRepository.deleteAll()
        ticketRepository.deleteAll()
        customerRepository.deleteAll()
        expertRepository.deleteAll()
        orderRepository.deleteAll()

        // CREATE CUSTOMERS
        val customer1 = Customer(
            "michele.galati@example.com",
            "michele",
            "galati"
        )
        val customer2 = Customer(
            "roberto.diciaula@example.com",
            "roberto",
            "di ciaula"
        )
        val customer3 = Customer(
            "michele.morgigno@example.com",
            "michele",
            "morgigno"
        )
        customerService.createCustomer(customer1.toDTO())
        customerService.createCustomer(customer2.toDTO())
        customerService.createCustomer(customer3.toDTO())

        // CREATE EXPERTS
        val expert1 = Expert(
            "1234",
            "antonio",
            "colella",
            "antonio.colella@example.com",
            "software"
        )
        val expert2 = Expert(
            "456789",
            "giulio",
            "fiorita",
            "giulio.fiorita@example.com",
            "pannelli solari"
        )
        val expert3 = Expert(
            "989890",
            "luca",
            "ruperto",
            "luca.ruperto@example.com",
            "security"
        )
        expertService.createExpert(expert1.toDTO())
        expertService.createExpert(expert2.toDTO())
        expertService.createExpert(expert3.toDTO())

        // CREATE PRODUCTS
        val product1 = Product(
            "prod1",
            "iPhone 11 pro",
        )
        val product2 = Product(
            "prod2",
            "MacBook Pro 14",
        )
        val product3 = Product(
            "prod3",
            "Samsung Galaxy S21",
        )
        productService.createProduct(product1.toDTO())
        productService.createProduct(product2.toDTO())
        productService.createProduct(product3.toDTO())

        // TIMESTAMP
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val timestamp1 = dateFormat.parse("2022-03-02")
        val timestamp2 = dateFormat.parse("2021-01-31")
        val timestamp3 = dateFormat.parse("2020-02-28")

        val warrantyDate1 = dateFormat.parse("2025-01-31")

        val order1 = OrderInputDTO(
            null,
            "michele.galati@example.com",
            "prod1",
            timestamp1,
            warrantyDate1
        )
        orderService.createOrder(order1)


        // CREATE TICKETS
        val ticket1 = TicketInputDTO(
            123456,
            timestamp1,
            "broken screen",
            null,
            TicketStatusValues.OPEN.name,
            expert1.id,
            product1.id,
            customer1.email
        )
        /*val ticket2 = TicketInputDTO(
            98776,
            timestamp2,
            "damaged battery",
            null,
            TicketStatusValues.OPEN.name,
            expert2.id,
            product2.id,
            customer2.email
        )
        val ticket3 = TicketInputDTO(
            45676,
            timestamp3,
            "low efficiency",
            null,
            TicketStatusValues.OPEN.name,
            expert3.id,
            product3.id,
            customer3.email
        )*/
        ticketService.createTicket(ticket1)
        /*ticketService.createTicket(ticket2)
        ticketService.createTicket(ticket3)*/

    }

    @Test
    fun `get message by ticketId`(){
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date1 = dateFormat.parse("2022-05-02")
        val date2 = dateFormat.parse("2022-05-02")
        val date3 = dateFormat.parse("2022-05-03")

        val message1 = MessageInputDTO(
            1,
            date1,
            "schermo distrutto",
            34567,
            "antonio"
        )
       /* val message2 = MessageInputDTO(
            2,
            date2,
            "la mia batteria non funziona!",
            123456,
            "raffaele"
        )
        val message3 = MessageInputDTO(
            3,
            date3,
            "risolveremo presto il suo problema",
            123456,
            "claudio"
        )*/
        messageService.postMessage(message1)
       // messageService.postMessage(message2)
       // messageService.postMessage(message3)

        val ticket = TicketInputDTO(
            123456,
            date1,
            "broken screen",
            null,
            TicketStatusValues.OPEN.name,
            "1357",
            "prod1",
            "michele.galati@example.com"
        )
        ticketService.createTicket(ticket)
        mockMvc.perform(
            MockMvcRequestBuilders.get("/API/messages/${ticket.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticket))
        ).andExpect(MockMvcResultMatchers.status().isOk)


    }
}