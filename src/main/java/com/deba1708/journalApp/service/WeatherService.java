package com.deba1708.journalApp.service;

import com.deba1708.journalApp.api.response.WeatherResponse;
import com.deba1708.journalApp.cache.AppCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class WeatherService {
    @Value("${weather.app.key}")
    private String apiKey;
    private final AppCache appCache;
    private final RestTemplate restTemplate;
    private final RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        } else{
            String finalAPI = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body != null) {
                redisService.set("weather_of_" + city,body, 300L);
            }
            return body;
        }
    }
    
}
