package com.finallab.account.service;

import com.finallab.account.model.Account;
import com.finallab.account.model.Address;
import com.finallab.account.repository.AccountRepository;
import com.finallab.account.summary.OrderSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger("AddressController");

    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account save(Account account){
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    public Account get(Long id){
        Optional<Account> temp = accountRepository.findById(id);
        Account result = temp.get();
        return result;
    }

    public void delete(Account account){
        //Address addressToDelete = addressService.getAddressByAccountId(account.getId());
        //addressService.delete(addressToDelete);
        accountRepository.delete(account);
    }

    public List<OrderSummary> listOrdersForAccountById(Long accountId){
        List<OrderSummary> results = accountRepository.findAllOrders(accountId);
        return results;
    }

}
