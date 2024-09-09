package com.deba1708.journalApp.service;

import com.deba1708.journalApp.entity.User;
import com.deba1708.journalApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepository.save(user);
        } catch (Exception e){
            log.error("Error for user {}",user.getUserName());
            log.warn("warning for {}",e.getMessage());
            log.info("info for user {}",e.getMessage());
            log.debug("debug for user {}",e.getMessage());
            log.trace("trace for user {}",e.getMessage());
        }
    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteByUserName(String username){userRepository.deleteByUserName(username);}

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}

//controller ---> service ---> repository
// in service we have to inject repository