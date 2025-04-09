package com.sirius1b.product.services;


import com.sirius1b.product.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {


    @KafkaListener(topics = Constants.TOPIC_PRODUCT_UPDATE)
    public void consume(ConsumerRecord<String, String> record) {
        log.info(String.format("Consumed message: %s", record.value()));
    }
}
