package it.polito.wa2.g35.server.ticketing.order

import com.fasterxml.jackson.databind.ObjectMapper
import it.polito.wa2.g35.server.products.Product
import it.polito.wa2.g35.server.products.ProductRepository
import it.polito.wa2.g35.server.products.ProductService
import it.polito.wa2.g35.server.products.toDTO
import it.polito.wa2.g35.server.profiles.customer.Customer
import it.polito.wa2.g35.server.profiles.customer.CustomerRepository
import it.polito.wa2.g35.server.profiles.customer.CustomerService
import it.polito.wa2.g35.server.profiles.customer.toDTO
import it.polito.wa2.g35.server.ticketing.ticket.TicketRepository
import it.polito.wa2.g35.server.ticketing.ticket.TicketService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.text.SimpleDateFormat
import java.util.*


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class OrderControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var customerService: CustomerService

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var productRepository: ProductRepository

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
    fun `create a new Order`(){
        // Given
        //orderRepository.deleteAll()

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 7) // imposta la data a 7 giorni nel futuro
        val futureDate = calendar.time

        val customer = Customer(
            "michele.galati@example.com",
            "michele",
            "galati"
        ).toDTO()
        customerService.postCustomer(customer)

        val product = Product(
            "prod1",
            "product1",
        ).toDTO()
        productService.postProduct(product)

        val order = OrderInputDTO(
            null,
            "michele.galati@example.com",
            "prod1",
            Date(),
            futureDate
        )
        orderService.createOrder(order)

        // When
        mockMvc.perform(
            MockMvcRequestBuilders.post("/API/orders/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(order)))
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.customer.email").value(customer.email))
            .andExpect(MockMvcResultMatchers.jsonPath("$.customer.name").value(customer.name))
            .andExpect(MockMvcResultMatchers.jsonPath("$.customer.surname").value(customer.surname))
            .andExpect(MockMvcResultMatchers.jsonPath("$.product.id").value(product.id))
            .andExpect(MockMvcResultMatchers.jsonPath("$.product.name").value(product.name))
    }
}