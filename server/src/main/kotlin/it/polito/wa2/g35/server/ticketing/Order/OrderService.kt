package it.polito.wa2.g35.server.ticketing.Order

interface OrderService {
    fun getOrders():List<OrderDTO>
}