package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.User;
import com.jerry.credit_card_reward_record.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(long userId) {

        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    public User saveUser(User user) {

        return userRepository.save(user);
    }

    public Boolean deleteUser(long userId) {

        boolean result = false;
        User user = findUserById(userId);
        if (user != null) {
            userRepository.delete(user);
            result = true;
        }
        return result;
    }
}
