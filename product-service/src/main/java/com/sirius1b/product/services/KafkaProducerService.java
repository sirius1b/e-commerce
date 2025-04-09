package com.sirius1b.product.services;

import com.sirius1b.product.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void productUpdate( String message){
        kafkaTemplate.send(Constants.TOPIC_PRODUCT_UPDATE, message);
    }

    public void proudctDelete(String message){
        kafkaTemplate.send(Constants.TOPIC_PRODUCT_DELETE, message);
    }
}
