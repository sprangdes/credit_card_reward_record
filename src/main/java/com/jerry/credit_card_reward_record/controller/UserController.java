package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Card;
import com.jerry.credit_card_reward_record.model.DTO.UserDTO;
import com.jerry.credit_card_reward_record.model.User;
import com.jerry.credit_card_reward_record.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId) {

        User user = userService.findUserById(userId);
        if(user != null) {
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.findAllUsers();
        if(!users.isEmpty()) {
            return ResponseEntity.ok(users);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<User> createOrUpdateUser(@RequestBody UserDTO userDTO) {

//        requestBody
//        有user_id執行更新，沒user_id執行新增
//
//        {
//            "user_id": 1,
//            "user_name": "Test User",
//            "password": "123456"
//        }

        User userCreated;
       if(userDTO.getUserId() == 0){
           userCreated = userService.saveUser(new User(userDTO.getUserName(), userDTO.getPassword()));
       }else{
           userCreated = userService.saveUser(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword()));
       }
       if(userCreated != null){
           return ResponseEntity.ok(userCreated);
       }else{
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/{userId}/card")
    public ResponseEntity<User> addCardToUser(@PathVariable long userId, @RequestParam("cardIds") List<Long> cardIds) {

        boolean result = userService.addCardsToUser(userId, cardIds);
        if(result){
            return ResponseEntity.ok().body(userService.findUserById(userId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable long userId) {

        boolean result = userService.deleteUser(userId);
        if(result){
            return ResponseEntity.ok("User deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userId}/recommend")
    public ResponseEntity<List<Card>> recommendCardsForUserByConsumption(@PathVariable long userId, @RequestParam("consumptionId") long consumptionId) {

        List<Card> cards = userService.recommendCardsForUserByConsumption(userId, consumptionId);
        if(cards != null){
            return ResponseEntity.ok(cards);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
