
package com.cal.CurrencyConversionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaService {

    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemoplate<String,String> kafkaTemplate;

    public void sendMessage(String message){
        System.out.println("sending to kafka: "+message);
        this.kafkaTemplate.send(TOPIC,message);
    }
}
