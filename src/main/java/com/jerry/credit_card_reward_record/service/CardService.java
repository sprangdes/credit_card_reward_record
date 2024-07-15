package com.jerry.credit_card_reward_record.service;

import com.jerry.credit_card_reward_record.model.Bank;
import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card findCardById(long cardId){

        return cardRepository.findById(cardId).orElse(null);
    }

    public List<Card> findAllCards(){

        return cardRepository.findAll();
    }

    public Card saveCard(Card card){

        return cardRepository.save(card);
    }

    public Boolean deleteCardById(long cardId){

        boolean result = false;
        Card card = findCardById(cardId);
        if(card != null) {
            cardRepository.delete(card);
            result = true;
        }
        return result;
    }
}
