package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.User;
import com.jerry.credit_card_reward_record.model.UserOwnCard;
import com.jerry.credit_card_reward_record.repository.CardRepository;
import com.jerry.credit_card_reward_record.repository.UserOwnCardRepository;
import com.jerry.credit_card_reward_record.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserOwnCardRepository userOwnCardRepository;

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

    public Boolean addCardToUser(long userId, long cardId) {

        boolean result = false;
        User user = findUserById(userId);
        Card card = cardRepository.findById(cardId).orElse(null);
        Set<Card> userCards = null;
        if (user != null && card != null) {
            userOwnCardRepository.save(new UserOwnCard(userId, cardId, 0.0f));
            userCards = user.getCards();
            userCards.add(card);
            user.setCards(userCards);
            userRepository.save(user);
            result = true;
            return result;
        }else{
            return result;
        }
    }
}
