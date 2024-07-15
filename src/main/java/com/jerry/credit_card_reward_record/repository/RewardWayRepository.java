package com.jerry.credit_card_reward_record.repository;

import com.jerry.credit_card_reward_record.model.RewardWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardWayRepository extends JpaRepository<RewardWay, Long> {
}
