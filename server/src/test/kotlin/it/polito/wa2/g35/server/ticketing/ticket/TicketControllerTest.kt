package it.polito.wa2.g35.server.ticketing.ticket

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.event.annotation.BeforeTestExecution
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



    }
    @Test
    fun `Given no tickets, when getTickets, then return empty list`() {
// Given
        ticketRepository.deleteAll()

        // When
        val result = mockMvc.perform(
            MockMvcRequestBuilders.get("/API/tickets")
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        // Then
        val returnedTickets = objectMapper.readValue(result.response.contentAsString, Array<TicketDTO>::class.java)
        assertEquals(0, returnedTickets.size)
    }

    /*@Test
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
    }*/
}
