package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.Bank;
import com.jerry.credit_card_reward_record.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank findBankById(long bankId) {

        return bankRepository.findById(bankId).orElse(null);
    }

    public List<Bank> findAllBanks() {

        return bankRepository.findAll();
    }

    public Bank saveBank(Bank bank) {

        return bankRepository.save(bank);
    }

    public Boolean deleteBank(long bankId) {

        boolean result = false;
        Bank bank = findBankById(bankId);
        if(bank != null) {
            bankRepository.delete(bank);
            result = true;
        }
        return result;
    }
}
