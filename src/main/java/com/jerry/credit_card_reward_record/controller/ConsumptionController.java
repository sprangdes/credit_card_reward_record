package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Consumption;
import com.jerry.credit_card_reward_record.model.DTO.ConsumptionDTO;
import com.jerry.credit_card_reward_record.service.ConsumptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumptions")
@Tag(name = "consumption api")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping("/{consumptionId}")
    @Operation(summary = "取得一筆消費通路", description = "以consumptionId取得消費通路", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Consumption"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Consumption> getConsumptionById(@PathVariable long consumptionId) {

        Consumption consumption = consumptionService.findConsumptionById(consumptionId);
        if(consumption != null) {
            return ResponseEntity.ok(consumption);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    @Operation(summary = "取得所有消費通路", description = "取得所有消費通路", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Consumption>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<Consumption>> getAllConsumptions() {

        List<Consumption> consumptions = consumptionService.findAllConsumptions();
        if(!consumptions.isEmpty()) {
            return ResponseEntity.ok(consumptions);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Operation(summary = "新增或更新消費通路", description = "有提供consumptionId時為更新；沒有提供consumptionId時為新增", responses = {
            @ApiResponse(responseCode = "201", description = "回傳Consumption"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Consumption> createOrUpdateConsumption(@RequestBody ConsumptionDTO consumptionDTO) {

        Consumption consumptionCreate;
        if(consumptionDTO.getConsumptionId() == 0){
            consumptionCreate = consumptionService.saveConsumption(new Consumption(consumptionDTO.getConsumptionName()));
        }else{
            consumptionCreate = consumptionService.saveConsumption(new Consumption(consumptionDTO.getConsumptionId(), consumptionDTO.getConsumptionName()));
        }
        if(consumptionCreate != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(consumptionCreate);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{consumptionId}")
    @Operation(summary = "刪除一筆消費通路", description = "依consumptionId刪除一筆消費通路", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Consumption deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteConsumptionById(@PathVariable long consumptionId) {

        boolean result = consumptionService.deleteConsumption(consumptionId);
        if(result){
            return ResponseEntity.ok("Deleted consumption successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
