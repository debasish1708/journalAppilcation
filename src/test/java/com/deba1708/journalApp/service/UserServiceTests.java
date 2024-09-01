package com.deba1708.journalApp.service;

import com.deba1708.journalApp.entity.User;
import com.deba1708.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Disabled
public class UserServiceTests {

    @Autowired
    private  UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "Debasish",
    })

    @Disabled
    public void testFindByUserName(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for name "+name);
//        User usr = userRepository.findByUserName("ram");
//        assertTrue(!usr.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,6"
    })

    @Disabled
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }
}
