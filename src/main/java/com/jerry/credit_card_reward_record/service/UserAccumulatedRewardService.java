package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.*;
import com.jerry.credit_card_reward_record.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserAccumulatedRewardService {

    @Autowired
    private UserAccumulatedRewardRepository userAccumulatedRewardRepository;

    @Autowired
    private RewardWayRepository rewardWayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private UserOwnCardService userOwnCardService;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    public void initializeUserAccumulatedReward(User user, Card card) {

        Set<RewardWay> rewardWays = card.getRewardWays();
        long userId = user.getUserId();
        UserAccumulatedReward userAccumulatedReward;
        for (RewardWay rewardWay : rewardWays) {
            long rewardWayId = rewardWay.getRewardWayId();
            userAccumulatedReward = new UserAccumulatedReward(userId, rewardWayId, 0);
            userAccumulatedRewardRepository.save(userAccumulatedReward);
        }
    }

    public Boolean updateUserAccumulatedReward(long userId, long rewardWayId, int price) {

        boolean result = false;
        UserAccumulatedReward userAccumulatedReward = userAccumulatedRewardRepository.findByUserIdAndRewardWayId(userId, rewardWayId);
        if(userAccumulatedReward != null) {
            RewardWay rewardWay = rewardWayRepository.findById(rewardWayId).get();
            int rewardWayLimit = rewardWay.getRewardLimit();
            int accumulateReward = userAccumulatedReward.getAccumulatedReward();
            if(rewardWayLimit > 0 && accumulateReward >= rewardWayLimit) {
                userAccumulatedReward.setAccumulatedReward(rewardWayLimit);
            } else if (rewardWayLimit == 0) {
                int record = accumulateReward + Math.round(price * rewardWay.getRewardRate());
                userAccumulatedReward.setAccumulatedReward(record);
            } else {
                int record = accumulateReward + Math.round(price * rewardWay.getRewardRate());
                if(record >= rewardWayLimit) {
                    userAccumulatedReward.setAccumulatedReward(rewardWayLimit);
                } else {
                    userAccumulatedReward.setAccumulatedReward(record);
                }
            }
            userAccumulatedRewardRepository.save(userAccumulatedReward);
            result = true;
            return result;
        }
        return result;
    }

    public List<UserAccumulatedReward> findByUserId(User user) {

        return userAccumulatedRewardRepository.findAllByUserId(user.getUserId());
    }

    public List<Card> recommendCardForUserByConsumption(long userId, long consumptionId) {

        List<UserAccumulatedReward> candidateRewardWay = new ArrayList<>();
        Consumption consumption = consumptionRepository.findById(consumptionId).get();
        User user = userRepository.findById(userId).get();
        List<UserOwnCard> userOwnCards = userOwnCardService.findByUserId(userId);
        List<UserAccumulatedReward> userAccumulatedRewards = findByUserId(user);
        for(UserAccumulatedReward userAccumulatedReward : userAccumulatedRewards) {
            long rewardWayId = userAccumulatedReward.getRewardWayId();
            Set<Consumption> consumptions = rewardWayRepository.findById(rewardWayId).get().getConsumptions();
            if(consumptions.contains(consumption)){
                candidateRewardWay.add(userAccumulatedReward);
            }
        }
        float bestRewardRate = 0.00f;
        for(UserAccumulatedReward userAccumulatedReward : candidateRewardWay){
            if(userAccumulatedReward.getAccumulatedReward() == rewardWayRepository.findById(userAccumulatedReward.getRewardWayId()).get().getRewardLimit()){
                candidateRewardWay.remove(userAccumulatedReward);
            }else{
                float candidateRewardRate = rewardWayRepository.findById(userAccumulatedReward.getRewardWayId()).get().getRewardRate();
                if(candidateRewardRate >= bestRewardRate){
                    bestRewardRate = candidateRewardRate;
                }else{
                    candidateRewardWay.remove(userAccumulatedReward);
                }
            }
        }
        if(candidateRewardWay.isEmpty()){
            return null;
        }else{
            List<Card> cards = new ArrayList<>();
            for(UserOwnCard userOwnCard : userOwnCards){
                cards.add(cardRepository.findById(userOwnCard.getCardId()).orElse(null));
            }
            for(UserAccumulatedReward userAccumulatedReward : candidateRewardWay){
                long rewardWayId = userAccumulatedReward.getRewardWayId();
                cards.removeIf(card -> !cardService.isRewardWayInCard(card.getCardId(), rewardWayId));
            }
            return cards;
        }
    }
}
