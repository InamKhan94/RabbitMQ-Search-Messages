package com.nisum.rabbitmq.messages.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messages {
    private Integer totalSearchedMessages;
    private Integer filteredMessageCount;
    private List<?> messages;
}
