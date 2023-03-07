package com.analytics.data.message;

import com.analytics.data.dto.CarPostDTO;
import com.analytics.data.service.PostAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerMessage {

    private final Logger LOG = (Logger) LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private PostAnalyticsService postAnalyticsService;

    @KafkaListener(topics = "car-post-topic", groupId = "analytics-posts-group")
    public void listening(CarPostDTO carPost){
        System.out.println("Received Car Post information:,"+carPost);
        LOG.info("ANALYTICS DATA - Received Car Post information: {}", carPost);
        postAnalyticsService.saveDataAnalytics(carPost);

    }
}
