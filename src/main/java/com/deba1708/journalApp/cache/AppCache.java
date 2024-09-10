package com.deba1708.journalApp.cache;

import com.deba1708.journalApp.entity.ConfigJournalAppEntity;
import com.deba1708.journalApp.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AppCache {

    private final ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> APP_CACHE;

    @PostConstruct
    public void init(){
        APP_CACHE=new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity : all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
