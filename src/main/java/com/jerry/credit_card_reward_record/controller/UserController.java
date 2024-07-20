package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.DTO.UserDTO;
import com.jerry.credit_card_reward_record.model.User;
import com.jerry.credit_card_reward_record.service.UserAccumulatedRewardService;
import com.jerry.credit_card_reward_record.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "user api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccumulatedRewardService userAccumulatedRewardService;

    @GetMapping("/{userId}")
    @Operation(summary = "取得一筆使用者資料", description = "以userId取得使用者資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳User"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<User> getUserById(@PathVariable long userId) {

        User user = userService.findUserById(userId);
        if(user != null) {
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    @Operation(summary = "取得所有使用者資料", description = "取得所有使用者資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<User>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.findAllUsers();
        if(!users.isEmpty()) {
            return ResponseEntity.ok(users);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Operation(summary = "新增或更新使用者資料", description = "有提供userId時為更新；沒有提供userId時為新增", responses = {
            @ApiResponse(responseCode = "201", description = "回傳User"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<User> createOrUpdateUser(@RequestBody UserDTO userDTO) {

        User userCreated;
       if(userDTO.getUserId() == 0){
           userCreated = userService.saveUser(new User(userDTO.getUserName(), userDTO.getPassword()));
       }else{
           userCreated = userService.saveUser(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword()));
       }
       if(userCreated != null){
           return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
       }else{
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/{userId}/card")
    @Operation(summary = "新增信用卡至使用者", description = "提供userId及cardIds以將多張信用卡新增至指定使用者", responses = {
            @ApiResponse(responseCode = "201", description = "回傳User"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<User> addCardToUser(@PathVariable long userId, @RequestParam("cardIds") List<Long> cardIds) {

        boolean result = userService.addCardsToUser(userId, cardIds);
        if(result){
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.findUserById(userId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "刪除一筆使用者資料", description = "依userId刪除一筆使用者資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteUserById(@PathVariable long userId) {

        boolean result = userService.deleteUser(userId);
        if(result){
            return ResponseEntity.ok("User deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/recommend")
    @Operation(summary = "依消費方式提供使用者可得到回饋的信用卡列表", description = "提供userId及consumptionId，回傳使用者所擁有的信用卡中，有對應消費方式回饋的信用卡列表", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Card>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<Card>> recommendCardsForUserByConsumption(@PathVariable long userId, @RequestParam("consumptionId") long consumptionId) {

        List<Card> cards = userService.recommendCardsForUserByConsumption(userId, consumptionId);
        if(cards != null){
            return ResponseEntity.ok(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{userId}/record")
    @Operation(summary = "新增一筆使用者消費回饋紀錄", description = "提供userId、rewardWayId及price，以新增一筆消費回饋紀錄至指定使用者", responses = {
            @ApiResponse(responseCode = "201", description = "回傳Record added successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> addRecord(@PathVariable long userId, @RequestParam("rewardWayId") long rewardWayId, @RequestParam("price") int price) {

        boolean result = userAccumulatedRewardService.updateUserAccumulatedReward(userId, rewardWayId, price);
        if(result){
            return ResponseEntity.status(HttpStatus.CREATED).body("Record added successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/recommend-consumption")
    @Operation(summary = "依消費通路提供使用者最高回饋的信用卡列表", description = "提供userId及consumptionId，回傳使用者所擁有的信用卡中，有對應消費方式最高回饋的信用卡列表", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Card>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<Card>> recommendCardForUserByConsumption(@PathVariable long userId, @RequestParam("consumptionId") long consumptionId) {

        List<Card> cards = userAccumulatedRewardService.recommendCardForUserByConsumption(userId, consumptionId);
        if(cards != null){
            return ResponseEntity.ok(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
