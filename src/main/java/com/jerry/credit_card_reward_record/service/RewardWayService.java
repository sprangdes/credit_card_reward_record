package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.Consumption;
import com.jerry.credit_card_reward_record.model.RewardWay;
import com.jerry.credit_card_reward_record.repository.CardRepository;
import com.jerry.credit_card_reward_record.repository.ConsumptionRepository;
import com.jerry.credit_card_reward_record.repository.RewardWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RewardWayService {

    @Autowired
    private RewardWayRepository rewardWayRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ConsumptionRepository consumptionRepository;

    public RewardWay findRewardWayById(long rewardWayId) {

        return rewardWayRepository.findById(rewardWayId).orElse(null);
    }

    public List<RewardWay> findAllRewardWays() {

        return rewardWayRepository.findAll();
    }

    public RewardWay findByRewardWayName(String rewardWayName) {

        return rewardWayRepository.findByRewardWayName(rewardWayName);
    }

    public List<RewardWay> findByCard(long cardId) {

        Card card = cardRepository.findById(cardId).orElse(null);
        if(card != null) {
            return rewardWayRepository.findByCard(card);
        }
        return null;
    }

    public RewardWay saveRewardWay(RewardWay rewardWay) {

        return rewardWayRepository.save(rewardWay);
    }

    public Boolean deleteRewardWay(long rewardWayId) {

        boolean result = false;
        RewardWay rewardWay = findRewardWayById(rewardWayId);
        if (rewardWay != null) {
            rewardWayRepository.delete(rewardWay);
            result = true;
        }
        return result;
    }

    public Boolean addConsumptionToRewardWay(long rewardWayId, long consumptionId) {

        boolean result = false;
        RewardWay rewardWay = findRewardWayById(rewardWayId);
        Consumption consumption = consumptionRepository.findById(consumptionId).orElse(null);
        Set<Consumption> consumptions = null;
        if(rewardWay != null && consumption != null) {
            consumptions = rewardWay.getConsumptions();
            consumptions.add(consumption);
            rewardWay.setConsumptions(consumptions);
            rewardWayRepository.save(rewardWay);
            result = true;
            return result;
        }else{
            return result;
        }
    }

    public Boolean deleteConsumptionInRewardWay(long rewardWayId, long consumptionId) {

        boolean result = false;
        RewardWay rewardWay = findRewardWayById(rewardWayId);
        Consumption consumption = consumptionRepository.findById(consumptionId).orElse(null);
        Set<Consumption> consumptions = rewardWay.getConsumptions();
        if(consumption != null && consumptions.contains(consumption)) {
            consumptions.remove(consumption);
            rewardWay.setConsumptions(consumptions);
            rewardWayRepository.save(rewardWay);
            result = true;
            return result;
        }else{
            return result;
        }
    }
}
