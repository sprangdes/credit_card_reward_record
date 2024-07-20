package com.jerry.credit_card_reward_record.repository;

import com.jerry.credit_card_reward_record.model.User;
import com.jerry.credit_card_reward_record.model.UserAccumulatedReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccumulatedRewardRepository extends JpaRepository<UserAccumulatedReward, Long> {

    List<UserAccumulatedReward> findAllByUserId(Long userId);

    UserAccumulatedReward findByUserIdAndRewardWayId(Long userId, Long rewardWayId);
}
