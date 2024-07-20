package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.DTO.RewardWayDTO;
import com.jerry.credit_card_reward_record.model.RewardWay;
import com.jerry.credit_card_reward_record.service.RewardWayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reward-ways")
@Tag(name = "reward way api")
public class RewardWayController {

    @Autowired
    private RewardWayService rewardWayService;

    @GetMapping("/{rewardWayId}")
    @Operation(summary = "取得一筆回饋方式", description = "以rewardWayId取得回饋方式", responses = {
            @ApiResponse(responseCode = "200", description = "回傳RewardWay"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<RewardWay> getRewardWayById(@PathVariable long rewardWayId) {

        RewardWay rewardWay = rewardWayService.findRewardWayById(rewardWayId);
        if(rewardWay != null) {
            return ResponseEntity.ok(rewardWay);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    @Operation(summary = "取得所有回饋方式", description = "取得所有回饋方式", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<RewardWay>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<RewardWay>> getAllRewardWays() {

        List<RewardWay> rewardWays = rewardWayService.findAllRewardWays();
        if(!rewardWays.isEmpty()) {
            return ResponseEntity.ok(rewardWays);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{rewardWayName}")
    @Operation(summary = "以回饋方式名稱取得一筆回饋方式", description = "以rewardWayName取得回饋方式", responses = {
            @ApiResponse(responseCode = "200", description = "回傳RewardWay"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<RewardWay> getRewardWayByRewardWayName(@PathVariable String rewardWayName) {

        RewardWay rewardWay = rewardWayService.findByRewardWayName(rewardWayName);
        if(rewardWay != null) {
            return ResponseEntity.ok().body(rewardWay);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/card/{cardId}")
    @Operation(summary = "以信用卡取得回饋方式列表", description = "以cardId取得回饋方式列表", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Card>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<RewardWay>> getRewardWaysByCard(@PathVariable long cardId) {

        List<RewardWay> rewardWays = rewardWayService.findByCard(cardId);
        if(!rewardWays.isEmpty()) {
            return ResponseEntity.ok(rewardWays);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Operation(summary = "新增或更新回饋方式", description = "有提供rewardWayId時為更新；沒有提供rewardWayId時為新增", responses = {
            @ApiResponse(responseCode = "201", description = "回傳RewardWay"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<RewardWay> createOrUpdateRewardWay(@RequestBody RewardWayDTO rewardWayDTO) {

        RewardWay rewardWayCreated;
        if(rewardWayDTO.getRewardWayId() == 0){
            rewardWayCreated = rewardWayService.saveRewardWay(new RewardWay(rewardWayDTO.getRewardWayName(), rewardWayDTO.getRewardLimit(), rewardWayDTO.getRewardRate()));
        }else{
            rewardWayCreated = rewardWayService.saveRewardWay(new RewardWay(rewardWayDTO.getRewardWayId(), rewardWayDTO.getRewardWayName(), rewardWayDTO.getRewardLimit(), rewardWayDTO.getRewardRate()));
        }
        if(rewardWayCreated != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(rewardWayCreated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{rewardWayId}/consumption")
    @Operation(summary = "新增多個消費通路至指定回饋方式", description = "提供rewardWayId及consumptionIds以將多個消費通路新增至指定回饋方式", responses = {
            @ApiResponse(responseCode = "201", description = "回傳List<RewardWay>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<RewardWay> addConsumptionsToRewardWay(@PathVariable long rewardWayId, @RequestParam("consumptionIds") List<Long> consumptionIds) {

        boolean result = rewardWayService.addConsumptionsToRewardWay(rewardWayId, consumptionIds);
        if(result){
            return ResponseEntity.ok().body(rewardWayService.findRewardWayById(rewardWayId));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rewardWayId}")
    @Operation(summary = "刪除一筆回饋方式", description = "依rewardWayId刪除一筆回饋方式", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Reward Way deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteRewardWayById(@PathVariable long rewardWayId) {

        boolean result = rewardWayService.deleteRewardWay(rewardWayId);
        if(result){
            return ResponseEntity.ok("RewardWay deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{rewardWayId}/consumption/{consumptionId}")
    @Operation(summary = "刪除一筆回饋方式中的消費通路", description = "提供rewardWayId及consumptionId刪除一筆回饋方式中的消費通路", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Consumption in Reward Way deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteConsumptionInRewardWay(@PathVariable long rewardWayId, @PathVariable long consumptionId) {

        boolean result = rewardWayService.deleteConsumptionInRewardWay(rewardWayId, consumptionId);
        if(result){
            return ResponseEntity.ok("Consumption in Reward Way deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
