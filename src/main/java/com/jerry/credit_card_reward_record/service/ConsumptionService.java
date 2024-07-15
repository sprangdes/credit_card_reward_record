package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.Consumption;
import com.jerry.credit_card_reward_record.repository.ConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    public Consumption findConsumptionById(Long consumptionId) {

        return consumptionRepository.findById(consumptionId).orElse(null);
    }

    public List<Consumption> findAllConsumptions() {

        return consumptionRepository.findAll();
    }

    public Consumption saveConsumption(Consumption consumption) {

        return consumptionRepository.save(consumption);
    }

    public Boolean deleteConsumption(Long consumptionId) {

        boolean result = false;
        Consumption consumption = findConsumptionById(consumptionId);
        if(consumption != null) {
            consumptionRepository.delete(consumption);
            result = true;
        }
        return result;
    }
}
