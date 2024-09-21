package com.deba1708.journalApp.scheduler;

import com.deba1708.journalApp.cache.AppCache;
import com.deba1708.journalApp.entity.JournalEntry;
import com.deba1708.journalApp.entity.User;
import com.deba1708.journalApp.enums.Sentiment;
import com.deba1708.journalApp.model.SentimentData;
import com.deba1708.journalApp.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserScheduler {

    private final UserRepositoryImpl userRepository;
    private final AppCache appCache;
    private final KafkaTemplate<String, SentimentData> kafkaTemplate;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepository.getUserForSA();
        for(User user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries
                    .stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minusDays(7)))
                    .map(JournalEntry::getSentiment)
                    .toList();

            Map<Sentiment, Integer> sentimentCount=new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment!=null)
                    sentimentCount.put(sentiment,
                            sentimentCount.getOrDefault(sentiment,0)+1);
            }

            int maxCount=0;
            Sentiment mostFrequentSentiment=null;
            for(Sentiment sentiment : sentimentCount.keySet()){
                if(sentimentCount.get(sentiment)>maxCount){
                    maxCount=sentimentCount.get(sentiment);
                    mostFrequentSentiment=sentiment;
                }
            }

            if(mostFrequentSentiment!=null){
                SentimentData sentimentData = SentimentData.builder()
                        .email(user.getEmail())
                        .sentiment("Sentiment for last 7 days " + mostFrequentSentiment)
                        .build();
                kafkaTemplate.send("weekly-sentiments", sentimentData.getEmail(), sentimentData);
            }

        }
    }

    @Scheduled(cron = "0 0 9 * * SUN")
    public void clearAppCache(){
        appCache.init();
    }
}
