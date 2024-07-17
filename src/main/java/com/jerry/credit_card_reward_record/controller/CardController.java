package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.DTO.CardDTO;
import com.jerry.credit_card_reward_record.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{cardId}")
    public ResponseEntity<Card> getCardById(@PathVariable long cardId) {


        Card card = cardService.findCardById(cardId);
        if(card != null){
            return ResponseEntity.ok().body(card);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Card>> getAllCards() {

        List<Card> cards = cardService.findAllCards();
        if(!cards.isEmpty()){
            return ResponseEntity.ok().body(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{cardName}")
    public ResponseEntity<Card> getCardByCardName(@PathVariable String cardName) {

        Card card = cardService.findByCardName(cardName);
        if(card != null){
            return ResponseEntity.ok().body(card);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bank/{bankId}")
    public ResponseEntity<List<Card>> getCardsByBank(@PathVariable long bankId) {

        List<Card> cards = cardService.findByBank(bankId);
        if(!cards.isEmpty()){
            return ResponseEntity.ok().body(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Card> createOrUpdateCard(@RequestBody CardDTO cardDTO) {

//        requestBody
//        有card_id執行更新，沒card_id執行新增
//
//        {
//            "card_id": 1,
//            "card_name": "Test Card",
//                "bank": {
//                    "bank_id": 1
//                }
//        }

        Card cardCreated;
        if(cardDTO.getCard_id() == 0){
            cardCreated = cardService.saveCard(new Card(cardDTO.getBank(), cardDTO.getCard_name()));
        }else{
            cardCreated = cardService.saveCard(new Card(cardDTO.getCard_id(), cardDTO.getBank(), cardDTO.getCard_name()));
        }
        if(cardCreated != null){
            return ResponseEntity.ok(cardCreated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cardId}/reward-way/{rewardWayId}")
    public ResponseEntity<Card> addRewardWayToCard(@PathVariable long cardId, @PathVariable long rewardWayId) {

        boolean result = cardService.addRewardWayToCard(cardId, rewardWayId);
        if(result){
            return ResponseEntity.ok().body(cardService.findCardById(cardId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> deleteCardById(@PathVariable long cardId) {

        boolean result = cardService.deleteCardById(cardId);
        if(result){
            return ResponseEntity.ok("card deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cardId}/reward-way/{rewardWayId}")
    public ResponseEntity<String> deleteRewardWayInCard(@PathVariable long cardId, @PathVariable long rewardWayId) {

        boolean result = cardService.deleteRewardWayInCard(cardId, rewardWayId);
        if(result){
            return ResponseEntity.ok().body("Reward Way in Card deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
