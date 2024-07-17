package com.jerry.credit_card_reward_record.repository;

import com.jerry.credit_card_reward_record.model.UserOwnCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOwnCardRepository extends JpaRepository<UserOwnCard, Long> {

    List<UserOwnCard> findByUserId(long userId);

    List<UserOwnCard> findByCardId(long cardId);

    UserOwnCard findByUserIdAndCardId(long userId, long cardId);
}
