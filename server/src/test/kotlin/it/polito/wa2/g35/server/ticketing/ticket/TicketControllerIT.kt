package it.polito.wa2.g35.server.ticketing.ticket

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import it.polito.wa2.g35.server.profiles.customer.CustomerService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TicketControllerIT(@Autowired val ticketService: TicketService, @Autowired val customerService: CustomerService) {
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

        @BeforeAll
        @JvmStatic
        fun setUpClass() {
            // before all tests
        }

        @AfterAll
        @JvmStatic
        fun tearDownClass() {
            // after all tests
        }
    }


    @LocalServerPort
    private val port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate
    @Autowired
    lateinit var ticketRepository: TicketRepository

    private lateinit var mockMvc: MockMvc

    private val objectMapper = ObjectMapper().registerModule(
        KotlinModule.Builder()
            .withReflectionCacheSize(512)
            .configure(KotlinFeature.NullToEmptyCollection, false)
            .configure(KotlinFeature.NullToEmptyMap, false)
            .configure(KotlinFeature.NullIsSameAsDefault, false)
            .configure(KotlinFeature.SingletonSupport, false)
            .configure(KotlinFeature.StrictNullChecks, false)
            .build()
    )

    @BeforeEach
    fun setup(mockMvc: MockMvc) {
        this.mockMvc = mockMvc
    }

    @Test
    fun `test getTickets endpoint`() {
        val result = mockMvc.perform(get("/API/tickets"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        val tickets = objectMapper.readValue(result.response.contentAsString, Array<TicketDTO>::class.java)

        // assert something about the response
    }

    @Test
    fun `test getTicketById endpoint`() {
        // create a ticket to retrieve later
        val ticketInput = TicketInputDTO(
            null,
            null,
            "Description 1",
            null,
            null,
            "1",
            "123",
            "test@example.com",
        )
        val createdTicket = ticketService.createTicket(ticketInput)

        val result = mockMvc.perform(get("/API/tickets/{ticketId}", createdTicket?.id))
            .andExpect(status().isOk)
    }
}

