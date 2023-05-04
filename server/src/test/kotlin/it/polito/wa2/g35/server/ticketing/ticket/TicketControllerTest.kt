package it.polito.wa2.g35.server.ticketing.ticket

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

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

        @JvmStatic
        @BeforeAll
        fun setup(ticketControllerTest: TicketControllerTest) {
            ticketControllerTest.ticketRepository.deleteAll()
            val ticket1 = TicketInputDTO(
                null,
                null,
                "description 1",
                TicketPriority.LOW.name,
                TicketStatusValues.OPEN.name,
                "123",
                "112233",
                "test1@example.com"
            )
            val ticket2 = TicketInputDTO(
                null,
                null,
                "description 2",
                TicketPriority.MEDIUM.name,
                TicketStatusValues.OPEN.name,
                "456",
                "445566",
                "test2@example.com"
            )
            val ticket3 = TicketInputDTO(
                null,
                null,
                "description 3",
                TicketPriority.HIGH.name,
                TicketStatusValues.CLOSED.name,
                "789",
                "778899",
                "test3@example.com"
            )
            ticketControllerTest.ticketService.createTicket(ticket1)
            ticketControllerTest.ticketService.createTicket(ticket2)
            ticketControllerTest.ticketService.createTicket(ticket3)
        }
    }

    @Test
    fun `Given no tickets, when getTickets, then return empty list`() {
        // Given
        //ticketRepository.deleteAll()

        // When
        val result = mockMvc
            .perform(MockMvcRequestBuilders.get("/API/tickets")
                .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // Then
        assertEquals(3, result.response.contentLength)
    }

    @Test
    fun `Given one ticket, when getTicketById, then return the ticket`() {
        // Given
        ticketRepository.deleteAll()
        val ticket = TicketInputDTO(null, null, "description", TicketPriority.LOW.name, TicketStatusValues.OPEN.name, "123", "112233", "test@example.com")
        val savedTicket = ticketService.createTicket(ticket)

        // When
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/API/tickets/${savedTicket?.id}")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // Then
        val returnedTicket = objectMapper.readValue(result.response.contentAsString, TicketDTO::class.java)
        assertEquals(savedTicket, returnedTicket)
    }
}
