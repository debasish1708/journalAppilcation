package com.deba1708.journalApp.service;

import com.deba1708.journalApp.scheduler.UserScheduler;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void testFetchUserAndSendSaMail(){
        userScheduler.fetchUserAndSendSaMail();
    }
}
