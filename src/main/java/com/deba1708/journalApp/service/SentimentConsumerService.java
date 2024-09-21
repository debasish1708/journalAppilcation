package com.deba1708.journalApp.service;


import com.deba1708.journalApp.model.SentimentData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentimentConsumerService {

    private final EmailService emailService;

    @KafkaListener(topics = "weekly-sentiments", groupId = "weekly-sentiments-group")
    public void consume(SentimentData sentimentData){
        sendEmail(sentimentData);
    }

    private void sendEmail(SentimentData sentimentData) {
        emailService.sendEmail(sentimentData.getEmail(),"Sentiment of previous week ",sentimentData.getSentiment());
    }
}
