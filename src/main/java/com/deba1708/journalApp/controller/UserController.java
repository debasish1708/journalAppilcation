package com.deba1708.journalApp.controller;

import com.deba1708.journalApp.api.response.WeatherResponse;
import com.deba1708.journalApp.entity.User;
import com.deba1708.journalApp.service.UserService;
import com.deba1708.journalApp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    private final WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userINDb = userService.findByUserName(userName);
        userINDb.setUserName(user.getUserName());
        userINDb.setPassword(user.getPassword());
        userService.saveNewUser(userINDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Bhubaneswar");
        String greeting="";
        if(weatherResponse != null){
            greeting=", Weather feels like "+weatherResponse.getCurrent().getTemperature();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greeting, HttpStatus.OK);
    }
}

//controller ---> service ---> repository
// in controller we have to inject service object