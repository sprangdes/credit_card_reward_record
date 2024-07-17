package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.DTO.RewardWayDTO;
import com.jerry.credit_card_reward_record.model.RewardWay;
import com.jerry.credit_card_reward_record.service.RewardWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reward-ways")
public class RewardWayController {

    @Autowired
    private RewardWayService rewardWayService;

    @GetMapping("/{rewardWayId}")
    public ResponseEntity<RewardWay> getRewardWayById(@PathVariable long rewardWayId) {

        RewardWay rewardWay = rewardWayService.findRewardWayById(rewardWayId);
        if(rewardWay != null) {
            return ResponseEntity.ok(rewardWay);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<RewardWay>> getAllRewardWays() {

        List<RewardWay> rewardWays = rewardWayService.findAllRewardWays();
        if(!rewardWays.isEmpty()) {
            return ResponseEntity.ok(rewardWays);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{rewardWayName}")
    public ResponseEntity<RewardWay> getRewardWayByRewardWayName(@PathVariable String rewardWayName) {

        RewardWay rewardWay = rewardWayService.findByRewardWayName(rewardWayName);
        if(rewardWay != null) {
            return ResponseEntity.ok().body(rewardWay);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<RewardWay>> getRewardWaysByCard(@PathVariable long cardId) {

        List<RewardWay> rewardWays = rewardWayService.findByCard(cardId);
        if(!rewardWays.isEmpty()) {
            return ResponseEntity.ok(rewardWays);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<RewardWay> createOrUpdateRewardWay(@RequestBody RewardWayDTO rewardWayDTO) {

//        requestBody
//        有reward_way_id執行更新，沒reward_way_id執行新增
//
//        {
//            "reward_way_id": 1,
//            "reward_way_name": "Test RewardWay 1",
//            "reward_limit": 200,
//            "reward_rate": 0.02
//        }

        RewardWay rewardWayCreated;
        if(rewardWayDTO.getReward_way_id() == 0){
            rewardWayCreated = rewardWayService.saveRewardWay(new RewardWay(rewardWayDTO.getReward_way_name(), rewardWayDTO.getReward_limit(), rewardWayDTO.getReward_rate()));
        }else{
            rewardWayCreated = rewardWayService.saveRewardWay(new RewardWay(rewardWayDTO.getReward_way_id(), rewardWayDTO.getReward_way_name(), rewardWayDTO.getReward_limit(), rewardWayDTO.getReward_rate()));
        }
        if(rewardWayCreated != null){
            return ResponseEntity.ok(rewardWayCreated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{rewardWayId}/consumption/{consumptionId}")
    public ResponseEntity<RewardWay> addConsumptionToRewardWay(@PathVariable long rewardWayId, @PathVariable long consumptionId) {

        boolean result = rewardWayService.addConsumptionToRewardWay(rewardWayId, consumptionId);
        if(result){
            return ResponseEntity.ok().body(rewardWayService.findRewardWayById(rewardWayId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rewardWayId}")
    public ResponseEntity<String> deleteRewardWayById(@PathVariable long rewardWayId) {

        boolean result = rewardWayService.deleteRewardWay(rewardWayId);
        if(result){
            return ResponseEntity.ok("RewardWay deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rewardWayId}/consumption/{consumptionId}")
    public ResponseEntity<String> deleteConsumptionInRewardWay(@PathVariable long rewardWayId, @PathVariable long consumptionId) {

        boolean result = rewardWayService.deleteConsumptionInRewardWay(rewardWayId, consumptionId);
        if(result){
            return ResponseEntity.ok("Consumption in Reward Way deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
