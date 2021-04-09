package com.nisum.rabbitmqsearchmessage.controller;


import com.nisum.rabbitmqsearchmessage.config.MessagingConfig;
import com.nisum.rabbitmqsearchmessage.dto.Messages;
import com.nisum.rabbitmqsearchmessage.dto.SearchParams;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.TimeoutException;
import com.sun.jmx.mbeanserver.Util;

@RestController
public class SearchMessages {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/searchMessages")
    public Messages getFilteredMessages(@RequestBody SearchParams searchParams) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        List<?> msgs = new ArrayList<>();
        LinkedHashMap<?, Object> msg = (LinkedHashMap<?, Object>) template.receiveAndConvert(searchParams.getQueueName());
        Messages msgObj = new Messages();
        List<?> filterMsgs = new ArrayList<>();;

        msgs.add(Util.cast(msg));
        while (msg != null){
            Set<String> keys = (Set<String>) msg.keySet();
            for (String key : keys) {
                Object value = msg.get(key);

                if(value.toString().toLowerCase().contains(searchParams.getSearchCriteria().toLowerCase())){
                    filterMsgs.add(Util.cast(msg));
                    break;
                }

            }
            msg = (LinkedHashMap<String, Object>) template.receiveAndConvert(searchParams.getQueueName());
            msgs.add(Util.cast(msg));
        }

        msgObj.setTotalMesssageCount(msgs.size()-1);
        msgObj.setMessages(filterMsgs);
        msgObj.setFilteredMessageCount(msgObj.getMessages().size());

        //publishing subscribed messages
        for (Object msgWrite: msgs) {
            if(msgWrite != null)
                template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, msgWrite);
        }

        return msgObj;
    }


}
