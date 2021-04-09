package com.nisum.rabbitmqsearchmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {

    private String userName;
    private String password;
    private String connectionURL;
    private String queueName;
    private String searchCriteria;
}
