package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.Bank;
import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.RewardWay;
import com.jerry.credit_card_reward_record.repository.BankRepository;
import com.jerry.credit_card_reward_record.repository.CardRepository;
import com.jerry.credit_card_reward_record.repository.RewardWayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private RewardWayRepository rewardWayRepository;

    public Card findCardById(long cardId) {

        return cardRepository.findById(cardId).orElse(null);
    }

    public Card findByCardName(String cardName) {

        return cardRepository.findByCardName(cardName).orElse(null);
    }

    public List<Card> findAllCards() {

        return cardRepository.findAll();
    }

    public List<Card> findByBank(long bankId) {

        Bank bank = bankRepository.findById(bankId).orElse(null);
        if(bank != null) {
            return cardRepository.findByBank(bank);
        }
        return null;
    }

    public Card saveCard(Card card) {

        return cardRepository.save(card);
    }

    public Boolean deleteCardById(long cardId) {

        boolean result = false;
        Card card = findCardById(cardId);
        if(card != null) {
            cardRepository.delete(card);
            result = true;
        }
        return result;
    }

    public Boolean addRewardWaysToCard(long cardId, List<Long> rewardWayIds) {

        boolean result = false;
        Card card = findCardById(cardId);
        if(card != null) {
            RewardWay newRewardWay;
            for(Long rewardWayId : rewardWayIds) {
                newRewardWay = rewardWayRepository.findById(rewardWayId).orElse(null);
                if(newRewardWay.getCard() == null){
                    newRewardWay.setCard(cardRepository.findById(cardId).orElse(null));
                    rewardWayRepository.save(newRewardWay);
                    result = true;
                    return result;
                }
            }
        }
        return result;
    }

    public Boolean deleteRewardWayInCard(long cardId, long rewardWayId) {

        boolean result = false;
        Card card = findCardById(cardId);
        RewardWay rewardWay = rewardWayRepository.findById(rewardWayId).orElse(null);
        Set<RewardWay> rewardWays = card.getRewardWays();
        if(rewardWay != null && rewardWays.contains(rewardWay)) {
            rewardWays.remove(rewardWay);
            card.setRewardWays(rewardWays);
            cardRepository.save(card);
            result = true;
            return result;
        }else{
            return result;
        }
    }
}
