package com.nisum.rabbitmq.messages.search.controller;


import com.nisum.rabbitmq.messages.search.dto.Messages;
import com.nisum.rabbitmq.messages.search.dto.SearchParams;
import com.nisum.rabbitmq.messages.search.service.SearchMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchMessagesController {

    @Autowired
    private SearchMessagesService searchMessageService;

    @PostMapping("/search-messages")
    public Messages getFilteredMessages(@RequestBody SearchParams searchParams) {
        return searchMessageService.getFilteredMessages(searchParams);
    }


}
