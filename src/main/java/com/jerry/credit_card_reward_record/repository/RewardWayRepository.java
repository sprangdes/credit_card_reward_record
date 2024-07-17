package com.jerry.credit_card_reward_record.repository;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.RewardWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardWayRepository extends JpaRepository<RewardWay, Long> {

    RewardWay findByRewardWayName(String rewardWayName);

    List<RewardWay> findByCard(Card card);
}
