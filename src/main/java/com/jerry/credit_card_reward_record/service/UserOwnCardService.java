package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.UserOwnCard;
import com.jerry.credit_card_reward_record.repository.UserOwnCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOwnCardService {

    @Autowired
    private UserOwnCardRepository userOwnCardRepository;

    public UserOwnCard findUserOwnCardById(long userOwnCardId) {

        return userOwnCardRepository.findById(userOwnCardId).orElse(null);
    }

    public List<UserOwnCard> findByUserId(long userId) {

        return userOwnCardRepository.findByUserId(userId);
    }

    public List<UserOwnCard> findByCardId(long cardId) {

        return userOwnCardRepository.findByCardId(cardId);
    }

    public UserOwnCard findByCardIdAndUserId(long cardId, long userId) {

        return userOwnCardRepository.findByUserIdAndCardId(cardId, userId);
    }

    public UserOwnCard save(UserOwnCard userOwnCard) {

        return userOwnCardRepository.save(userOwnCard);
    }

    public Boolean deleteUserOwnCardById(long userOwnCardId) {

        boolean result = false;
        UserOwnCard userOwnCard = findUserOwnCardById(userOwnCardId);
        if (userOwnCard != null) {
            userOwnCardRepository.delete(userOwnCard);
            result = true;
        }
        return result;
    }
}
