package com.graphnetwork.network.Service;

import com.graphnetwork.network.Dto.KafaMessagedto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, KafaMessagedto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        // Configure JsonDeserializer
        JsonDeserializer<KafaMessagedto> jsonDeserializer = new JsonDeserializer<>(KafaMessagedto.class, false);
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("*");

        // ErrorHandlingDeserializer needs to be initialized with the actual deserializer
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, jsonDeserializer);

        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(jsonDeserializer));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafaMessagedto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafaMessagedto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Define a DefaultErrorHandler
        DefaultErrorHandler errorHandler = new DefaultErrorHandler();

        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}
