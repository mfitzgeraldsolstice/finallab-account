package com.finallab.account.controller;

import com.finallab.account.model.Account;
import com.finallab.account.model.Address;
import com.finallab.account.repository.AccountRepository;
import com.finallab.account.service.AccountService;
import com.finallab.account.summary.OrderSummary;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class AccountController {
    Logger logger = LoggerFactory.getLogger("AccountController");

   AccountService accountService;

    /*public AccountController(AccountService accountService){
        this.accountService = accountService;
    }*/

    @PostMapping("/account")
    @HystrixCommand(fallbackMethod = "saveAccountFallback")
    public Account save(@RequestBody Account account){
        Account savedAccount = accountService.save(account);
        return savedAccount;
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "getAccountFallback")
    public Account get(@PathVariable("id") Long id){
        Account result = accountService.get(id);
        return result;
    }

    @PutMapping("/account/update")
    @HystrixCommand(fallbackMethod = "updateAccountFallback")
    public Account update(@RequestBody Account account){
        Account updatedAccount = accountService.save(account);
        return updatedAccount;
    }

    @DeleteMapping("/account/delete")
    @HystrixCommand(fallbackMethod = "deleteAccountFallback")
    public void delete(Account account){
        accountService.delete(account);
    }

    @GetMapping("/orders?accountId={accountId}")
    @HystrixCommand(fallbackMethod = "listOrdersForAccountFallback")
    public List<OrderSummary> listOrdersForAccountById(@PathVariable("accountId") Long accountId){
        List<OrderSummary> results = accountService.listOrdersForAccountById(accountId);
        return results;
    }

    public Account saveAccountFallback(Account account){
        logger.error("Error saving account: " + account);
        return new Account();
    }

    public Account getAccountFallback(Account account){
        logger.error("Error getting account: " + account);
        return new Account();
    }

    public Account updateAccountFallback(Account account){
        logger.error("Error updating account: " + account);
        return new Account();
    }

    public Account deleteAccountFallback(Account account){
        logger.error("Error deleting account: " + account);
        return new Account();
    }

    public List<OrderSummary> listOrdersForAccountFallback(List<OrderSummary> orderSummaries){
        logger.error("Error listing orders for account: " + orderSummaries);
        return new ArrayList<>();
    }
}
