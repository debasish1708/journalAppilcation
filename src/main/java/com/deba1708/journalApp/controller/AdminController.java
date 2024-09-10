package com.deba1708.journalApp.controller;

import com.deba1708.journalApp.cache.AppCache;
import com.deba1708.journalApp.entity.User;
import com.deba1708.journalApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAll() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("clear-app-cache")
    public void AppCache(){
        appCache.init();
    }
}
