package com.nisum.rabbitmq.messages.search.service;

import com.nisum.rabbitmq.messages.search.dto.Messages;
import com.nisum.rabbitmq.messages.search.dto.SearchParams;
import com.sun.jmx.mbeanserver.Util;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

@Service
public class SearchMessagesService {

    @Autowired
    private RabbitTemplate template;

    public Messages getFilteredMessages(SearchParams searchParams){

        List<?> totalMessages = new ArrayList<>();
        List<?> filteredMessages = new ArrayList<>();

        LinkedHashMap<?, Object> retreivedMsg = (LinkedHashMap<?, Object>) template.receiveAndConvert(searchParams.getQueueName());
        totalMessages.add(Util.cast(retreivedMsg));

        while (retreivedMsg != null){
            Set<String> keys = (Set<String>) retreivedMsg.keySet();
            for (String key : keys) {
                Object value = retreivedMsg.get(key);

                if(value.toString().toLowerCase().contains(searchParams.getSearchCriteria().toLowerCase())){
                    filteredMessages.add(Util.cast(retreivedMsg));
                    break;
                }
            }

            if(filteredMessages.size() == searchParams.getNoOfRecordsRequired())
                break;

            retreivedMsg = (LinkedHashMap<String, Object>) template.receiveAndConvert(searchParams.getQueueName());
            if(retreivedMsg != null)
                totalMessages.add(Util.cast(retreivedMsg));
        }

        // publishing subscribed messages
        for (Object msgWrite: totalMessages)
            template.convertAndSend(searchParams.getExchangeName(), searchParams.getRoutingKey(), msgWrite);

        // preparing Filter Messages Response
        Messages responseMessage = new Messages();
        responseMessage.setTotalSearchedMessages(totalMessages.size());
        responseMessage.setMessages(filteredMessages);
        responseMessage.setFilteredMessageCount(responseMessage.getMessages().size());

        return responseMessage;
    }
}
