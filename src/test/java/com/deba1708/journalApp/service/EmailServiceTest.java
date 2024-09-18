package com.deba1708.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("dasdebasish.dd77@gmail.com"
                ,"Testing java mail Sender"
                ,"Hi, I am java mail sender");
    }
}
