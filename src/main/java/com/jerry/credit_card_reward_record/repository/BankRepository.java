package com.jerry.credit_card_reward_record.repository;

import com.jerry.credit_card_reward_record.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
