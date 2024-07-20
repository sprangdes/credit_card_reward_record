package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.*;
import com.jerry.credit_card_reward_record.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private UserAccumulatedRewardService userAccumulatedRewardService;

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

    public Boolean addCardsToUser(long userId, List<Long> cardIds) {

        boolean result = false;
        User user = findUserById(userId);
        if(user != null){
            Set<Card> originalCards = user.getCards();
            for(Long cardId : cardIds){
                Card newCard = cardRepository.findById(cardId).orElse(null);
                if(newCard != null){
                    originalCards.add(newCard);
                    Set<User> originalUsers = newCard.getUsers();
                    originalUsers.add(user);
                    newCard.setUsers(originalUsers);
                    cardRepository.save(newCard);
                    userOwnCardRepository.save(new UserOwnCard(userId, cardId));
                    userAccumulatedRewardService.initializeUserAccumulatedReward(user, newCard);
                }
            }
            user.setCards(originalCards);
            userRepository.save(user);
            result = true;
            return result;
        }
        return result;
    }

    public List<Card> recommendCardsForUserByConsumption(long userId, long consumptionId) {

        Set<Card> userCards = userRepository.findById(userId).orElse(null).getCards();
        Consumption consumption = consumptionRepository.findById(consumptionId).orElse(null);
        List<Card> recommendedCards = new ArrayList<>();
        for(Card userCard : userCards){
            Set<RewardWay> rewardWays = userCard.getRewardWays();
            for(RewardWay rewardWay : rewardWays){
                Set<Consumption> consumptions = rewardWay.getConsumptions();
                if(consumptions.contains(consumption)){
                    recommendedCards.add(userCard);
                }
            }
        }
        return recommendedCards;
    }
}
