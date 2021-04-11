# RabbitMQ-Search-Messages

This Spring boot RestAPI can search published messages on RabbitMQ queue based on two fiters which are string thats need to be search in messages and no of search messages needs to be fetched for the matched string.

Request:
{   
    "queueName":"QUEUE.2",
    "searchCriteria":"hello",
    "exchangeName":"EXCHANGE.1",
    "routingKey":"ROUTING_KEY.1",
    "noOfRecordsRequired":15
}
