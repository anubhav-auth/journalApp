package com.unholy.journalApp.service;

import com.unholy.journalApp.cache.AppCache;
import com.unholy.journalApp.entity.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Value("${weather_api_key}")
    private String apiKey;
//    private static final String api = "https://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherApi getWeather(String city) {
        WeatherApi weatherResponse = redisService.get("weather_of_" + city, WeatherApi.class);
        if (weatherResponse != null){
            return weatherResponse;
        }else {
            String finalAPI = appCache.APP_CACHE.get("weatherApi").replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherApi> weatherApiResponseEntity = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherApi.class);
            WeatherApi body = weatherApiResponseEntity.getBody();
            if(body != null){
                redisService.set("weather_of_" + city, body, 300L);
            }
            return body;
        }


    }

    /* if you want to send a post request then need to make a request body
     */

//    public WeatherApi postWeather(String city) {
//        String finalAPI = api.replace("CITY", city).replace("API_KEY", apiKey);
//
//        User user = User.builder().username("Anubhav").password("fnuergf").build();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key", "value");
//
//
//        HttpEntity<User> httpEntity = new HttpEntity<>(user, httpHeaders);//just send user or can also send headers
//
//        ResponseEntity<WeatherApi> weatherApiResponseEntity = restTemplate.exchange(finalAPI, HttpMethod.POST, httpEntity, WeatherApi.class);
//        //this will send a post request for a new user
//
//        WeatherApi body = weatherApiResponseEntity.getBody();
//        return body;
//    }

}
