# API

## Ticket

### GET

### POST

#### /ticket
RequestBody : TicketDTO \
Response: 201 Created, 422 Bad Request, 500 Server Error

Lo stato del Ticket è OPEN, solo un Customer può eseguire quest'azione

Service : createTicket, bisogna controllare che l'ordine di questo prodotto per il customer esista e che la garanzia sia ancora valida

### PUT

#### /ticket/:ticketId
RequestBody : TicketDTO \
Response: 201 Created, 422 Bad Request, 500 Server Error


## Message

### GET

#### /message/:ticketId

Get messages related to ticketId

RequestBody: Empty
Response : MessageDTO

### POST

#### /message

RequestBody : MessageDTO \
Response: 201 Created, 422 Bad Request, 500 Server Error

## TicketStatus

### POST

#### /status/:ticketId

RequestBody: TicketStatusDTO \
Constraints: se status == OPEN, solo Manager può impostare IN PROGRESS

## Attachment