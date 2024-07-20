package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.DTO.CardDTO;
import com.jerry.credit_card_reward_record.service.BankService;
import com.jerry.credit_card_reward_record.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@Tag(name = "card api")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private BankService bankService;

    @GetMapping("/{cardId}")
    @Operation(summary = "取得一筆信用卡資料", description = "以cardId取得信用卡資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Card"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Card> getCardById(@PathVariable long cardId) {


        Card card = cardService.findCardById(cardId);
        if(card != null){
            return ResponseEntity.ok().body(card);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    @Operation(summary = "取得所有信用卡資料", description = "取得所有信用卡資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Card>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<Card>> getAllCards() {

        List<Card> cards = cardService.findAllCards();
        if(!cards.isEmpty()){
            return ResponseEntity.ok().body(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{cardName}")
    @Operation(summary = "以信用卡名稱取得一筆信用卡資料", description = "以cardName取得信用卡資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Card"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Card> getCardByCardName(@PathVariable String cardName) {

        Card card = cardService.findByCardName(cardName);
        if(card != null){
            return ResponseEntity.ok().body(card);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bank/{bankId}")
    @Operation(summary = "以發卡銀行取得信用卡資料列表", description = "以bankId取得信用卡資料列表", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Card>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<Card>> getCardsByBank(@PathVariable long bankId) {

        List<Card> cards = cardService.findByBank(bankId);
        if(!cards.isEmpty()){
            return ResponseEntity.ok().body(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Operation(summary = "新增或更新信用卡資料", description = "有提供cardId時為更新；沒有提供cardId時為新增", responses = {
            @ApiResponse(responseCode = "201", description = "回傳Card"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Card> createOrUpdateCard(@RequestBody CardDTO cardDTO) {

        Card cardCreated;
        if(cardDTO.getCardId() == 0){
            cardCreated = cardService.saveCard(new Card(bankService.findBankById(cardDTO.getBankId()), cardDTO.getCardName()));
        }else{
            cardCreated = cardService.saveCard(new Card(cardDTO.getCardId(), bankService.findBankById(cardDTO.getBankId()), cardDTO.getCardName()));
        }
        if(cardCreated != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(cardCreated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cardId}/reward-way")
    @Operation(summary = "新增回饋方式至信用卡", description = "提供cardId及rewardWayIds以將多個回饋方式新增至指定信用卡", responses = {
            @ApiResponse(responseCode = "201", description = "回傳Card"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Card> addRewardWaysToCard(@PathVariable long cardId, @RequestParam("rewardWayIds") List<Long> rewardWayIds) {

        boolean result = cardService.addRewardWaysToCard(cardId, rewardWayIds);
        if(result){
            return ResponseEntity.ok().body(cardService.findCardById(cardId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cardId}")
    @Operation(summary = "刪除一筆信用卡資料", description = "依cardId刪除一筆信用卡資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Card deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteCardById(@PathVariable long cardId) {

        boolean result = cardService.deleteCardById(cardId);
        if(result){
            return ResponseEntity.ok("card deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cardId}/reward-way/{rewardWayId}")
    @Operation(summary = "刪除一筆信用卡中的回饋方式", description = "提供cardId及rewardWayId刪除一筆信用卡中的回饋方式", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Reward Way in Card deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteRewardWayInCard(@PathVariable long cardId, @PathVariable long rewardWayId) {

        boolean result = cardService.deleteRewardWayInCard(cardId, rewardWayId);
        if(result){
            return ResponseEntity.ok().body("Reward Way in Card deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
