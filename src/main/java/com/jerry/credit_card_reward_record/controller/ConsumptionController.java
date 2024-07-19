package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Consumption;
import com.jerry.credit_card_reward_record.model.DTO.ConsumptionDTO;
import com.jerry.credit_card_reward_record.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/consumptions")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping("/{consumptionId}")
    public ResponseEntity<Consumption> getConsumptionById(@PathVariable long consumptionId) {

        Consumption consumption = consumptionService.findConsumptionById(consumptionId);
        if(consumption != null) {
            return ResponseEntity.ok(consumption);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Consumption>> getAllConsumptions() {

        List<Consumption> consumptions = consumptionService.findAllConsumptions();
        if(!consumptions.isEmpty()) {
            return ResponseEntity.ok(consumptions);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Consumption> createOrUpdateConsumption(@RequestBody ConsumptionDTO consumptionDTO) {

//        requestBody
//        有consumption_id執行更新，沒consumption_id執行新增
//
//        {
//            "consumption_id": 1,
//            "consumption_name": "Test Consumption"
//        }

        Consumption consumptionCreate;
        if(consumptionDTO.getConsumptionId() == 0){
            consumptionCreate = consumptionService.saveConsumption(new Consumption(consumptionDTO.getConsumptionName()));
        }else{
            consumptionCreate = consumptionService.saveConsumption(new Consumption(consumptionDTO.getConsumptionId(), consumptionDTO.getConsumptionName()));
        }
        if(consumptionCreate != null){
            return ResponseEntity.ok(consumptionCreate);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{consumptionId}")
    public ResponseEntity<String> deleteConsumptionById(@PathVariable long consumptionId) {

        boolean result = consumptionService.deleteConsumption(consumptionId);
        if(result){
            return ResponseEntity.ok("Deleted consumption successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
