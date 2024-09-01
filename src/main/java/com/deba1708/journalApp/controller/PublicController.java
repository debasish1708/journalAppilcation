package com.deba1708.journalApp.controller;

import com.deba1708.journalApp.entity.User;
import com.deba1708.journalApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/public")
class PublicController {


    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        try{
            userService.saveNewUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e){
            log.error("{} username is already exist",user.getUserName());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
