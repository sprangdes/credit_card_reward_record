package com.jerry.credit_card_reward_record.controller;

import com.jerry.credit_card_reward_record.model.Bank;
import com.jerry.credit_card_reward_record.model.DTO.BankDTO;
import com.jerry.credit_card_reward_record.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banks")
@Tag(name = "bank api")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping("/{bankId}")
    @Operation(summary = "取得一筆發卡銀行資料", description = "以bankId取得發卡銀行資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Bank"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Bank> getBankById(@PathVariable Long bankId) {

        Bank bank = bankService.findBankById(bankId);
        if(bank != null){
            return ResponseEntity.ok(bank);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    @Operation(summary = "取得所有發卡銀行資料", description = "取得所有發卡銀行資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳List<Bank>"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<List<Bank>> getAllBanks() {

        List<Bank> banks = bankService.findAllBanks();
        if(!banks.isEmpty()){
            return ResponseEntity.ok(banks);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{bankName}")
    @Operation(summary = "以發卡銀行名稱取得一筆發卡銀行資料", description = "以bankName取得發卡銀行資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Bank"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Bank> getBankByBankName(@PathVariable String bankName) {

        Bank bank = bankService.findByBankName(bankName);
        if(bank != null){
            return ResponseEntity.ok(bank);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @Operation(summary = "新增或更新發卡銀行資料", description = "有提供bankId時為更新；沒有提供bankId時為新增", responses = {
            @ApiResponse(responseCode = "201", description = "回傳Bank"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<Bank> createOrUpdateBank(@RequestBody BankDTO bankDTO) {

        Bank bankCreated;
        if(bankDTO.getBankId() == 0){
            bankCreated = bankService.saveBank(new Bank(bankDTO.getBankName()));
        }else{
            bankCreated = bankService.saveBank(new Bank(bankDTO.getBankId(), bankDTO.getBankName()));
        }
        if(bankCreated != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(bankCreated);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bankId}")
    @Operation(summary = "刪除一筆發卡銀行資料", description = "依bankId刪除一筆發卡銀行資料", responses = {
            @ApiResponse(responseCode = "200", description = "回傳Bank deleted successfully"),
            @ApiResponse(responseCode = "404", description = "page not found")})
    public ResponseEntity<String> deleteBankById(@PathVariable Long bankId) {

        boolean result = bankService.deleteBank(bankId);
        if(result){
            return ResponseEntity.ok("Bank deleted successfully");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
