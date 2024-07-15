package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Bank;
import com.jerry.credit_card_reward_record.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/{bankId}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long bankId) {

        Bank bank = bankService.findBankById(bankId);
        if(bank != null){
            return ResponseEntity.ok(bank);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Bank>> getAllBanks() {

        List<Bank> banks = bankService.findAllBanks();
        if(!banks.isEmpty()){
            return ResponseEntity.ok(banks);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Bank> createOrUpdateBank(@RequestBody Bank bank) {

//        requestBody
//        有bank_id執行更新，沒bank_id執行新增
//
//        {
//            "bank_id": 1,
//            "bank_name": "Test Bank"
//        }

        Bank bankCreated = bankService.saveBank(bank);
        if(bankCreated != null){
            return ResponseEntity.ok(bankCreated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bankId}")
    public ResponseEntity<String> deleteBankById(@PathVariable Long bankId) {

        boolean result = bankService.deleteBank(bankId);
        if(result){
            return ResponseEntity.ok("Bank deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
