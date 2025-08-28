package com.wipro.payment.config;


import com.wipro.payment.entity.Payment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerConfigApp {

 @Bean
 public ConsumerFactory<String, Payment> consumerFactory() {
     Map<String, Object> configProps = new HashMap<>();
     configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
     configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "payment-group");
     configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
     configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
     configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
     return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new JsonDeserializer<>(Payment.class));
 }

 @Bean
 public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory() {
     ConcurrentKafkaListenerContainerFactory<String, Payment> factory = new ConcurrentKafkaListenerContainerFactory<>();
     factory.setConsumerFactory(consumerFactory());
     return factory;
 }
}
