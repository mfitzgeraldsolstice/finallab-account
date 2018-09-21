package com.finallab.account.service;

import com.finallab.account.model.Account;
import com.finallab.account.model.Address;
import com.finallab.account.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger("AddressService");

    AddressRepository addressRepository;
    AccountService accountService;

    public AddressService(AddressRepository addressRepository, AccountService accountService){
        this.addressRepository = addressRepository;
        this.accountService = accountService;
    }

    public Address save(Address address, Long id){
        address.setAccountId(id);
        Address addressSaved = addressRepository.save(address);
        Account accountToUpdate = accountService.get(id);
        accountToUpdate.setAddressId(addressSaved.getAddressId());
        accountService.save(accountToUpdate);
        return addressSaved;
    }

    public void delete(Address address){
        addressRepository.delete(address);
        Account accountToUpdate = accountService.get(address.getAccountId());
        accountToUpdate.setAddressId(null);
        accountService.save(accountToUpdate);
    }

    public Address getAddressByAccountId(Long accountId){
        return addressRepository.getAddressByAccountId(accountId);
    }

}
