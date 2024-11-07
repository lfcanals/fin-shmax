package com.systematicdata.shmax.modules.aggregator.bus;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.systematicdata.shmax.data.TickPrice;

/**
 * PriceTick consumer.
 */
@Component
public class KafkaTickPriceConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.tickprice.raw}", 
                  groupId = "aggregator")
    public void consume(final TickPrice tickPrice) {
        tickPrice.setAggregationTime(System.currentTimeMillis());
        System.out.println("message = " + tickPrice + ", thread=" 
                + Thread.currentThread());
        System.out.println("Total Latency (ms) : " + (tickPrice.getAggregationTime()
                - tickPrice.getVenueTime()));
        System.out.println("Bus Latency (ms) : " + (tickPrice.getL0()));
    }
}
