package com.systematicdata.shmax.modules.aggregator.config;

import java.util.HashMap; 
import java.util.Map; 
import org.apache.kafka.clients.consumer.ConsumerConfig; 
import org.apache.kafka.common.serialization.StringDeserializer; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.kafka.annotation.EnableKafka; 
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory; 
import org.springframework.kafka.core.ConsumerFactory; 
import org.springframework.kafka.core.DefaultKafkaConsumerFactory; 
  

/**
 * At this moment it's just the first approach.
 * It must be placed into an editable file.
 */
@EnableKafka
@Configuration
public class KafkaConfig { 
    @Bean
    public ConsumerFactory<String, String> consumerFactory() 
    { 
  
        // Creating a Map of string-object pairs 
        Map<String, Object> config = new HashMap<>(); 
  
        // Adding the Configuration 
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, 
                   "127.0.0.1:9092"); 
        config.put(ConsumerConfig.GROUP_ID_CONFIG, 
                   "group_id"); 
        config.put( 
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
            StringDeserializer.class); 
        config.put( 
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
            StringDeserializer.class); 
  
        return new DefaultKafkaConsumerFactory<>(config); 
    } 
  

    /**
     * Creates Listener Factory.
     */
    public ConcurrentKafkaListenerContainerFactory 
            concurrentKafkaListenerContainerFactory() { 
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                    new ConcurrentKafkaListenerContainerFactory<>(); 
        factory.setConsumerFactory(consumerFactory()); 
        return factory; 
    } 
}
