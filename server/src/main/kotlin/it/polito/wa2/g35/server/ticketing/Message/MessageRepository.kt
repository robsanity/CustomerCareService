package it.polito.wa2.g35.server.ticketing.Message;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
interface MessageRepository: JpaRepository<Message, Long>