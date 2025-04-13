package com.sirius1b.product.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sirius1b.product.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventConsumerService {

    @KafkaListener(topics = Constants.TOPIC_PRODUCT_UPDATE)
    public void update(ConsumerRecord<String, String> record) throws JsonProcessingException {
        log.info(String.format("Consumed message(update): %s", record.value()));
        ObjectMapper mapper = new ObjectMapper();
    }

    @KafkaListener(topics = Constants.TOPIC_PRODUCT_DELETE)
    public void delete(ConsumerRecord<String, String> record) {
        log.info(String.format("Consumed message(delete): %s", record.value()));
    }
}
