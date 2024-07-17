package com.jerry.credit_card_reward_record.repository;

import com.jerry.credit_card_reward_record.model.Bank;
import com.jerry.credit_card_reward_record.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByCardName(String cardName);

    List<Card> findByBank(Bank bank);
}
