package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.RewardWay;
import com.jerry.credit_card_reward_record.repository.RewardWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardWayService {

    @Autowired
    private RewardWayRepository rewardWayRepository;

    public RewardWay findRewardWayById(long rewardWayId) {

        return rewardWayRepository.findById(rewardWayId).orElse(null);
    }

    public List<RewardWay> findAllRewardWays() {

        return rewardWayRepository.findAll();
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
}
