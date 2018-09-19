package com.finallab.account.controller;

import com.finallab.account.service.AddressService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.finallab.account.model.Address;
import com.finallab.account.repository.AccountRepository;
import com.finallab.account.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("")
public class AddressController {

    Logger logger = LoggerFactory.getLogger("AddressController");

    AddressService addressService;

    /*public AddressController(AddressService addressService){
        this.addressService = addressService;
    }*/

    @PostMapping("/accounts/{id}/address")
    @HystrixCommand(fallbackMethod = "saveAddressFallback")
    public Address saveAddress(@RequestBody Address address, @PathVariable("id") Long id){

        if(address.getAccountId() == null){
            logger.info("Account is null");
        }
        else{
            logger.info("Account is not null");
        }

        Address savedAddress = addressService.save(address, id);

        logger.info("Address Saved :" + savedAddress.toString());

        return savedAddress;
    }

    @GetMapping("/accounts/{id}/address")
    @HystrixCommand(fallbackMethod = "getAddressByAccountIdFallback")
    public Address getAddressByAccountId(@PathVariable("id") Long accountId){
        Address result = addressService.getAddressByAccountId(accountId);
        return result;
    }

    @PutMapping("/address/update")
    @HystrixCommand(fallbackMethod = "updateAddressFallback")
    public Address update(@RequestBody Address address){
        Address updatedAddress = addressService.save(address, address.getAccountId());
        return updatedAddress;
    }

    public Address saveAddressFallback(Address address){
        logger.error("Error saving address: " + address);
        return new Address();
    }

    public Address getAddressByAccountIdFallback(Address address){
        logger.error("Error getting address by account id: " + address);
        return new Address();
    }

    public Address updateAddressFallback(Address address){
        logger.error("Error updating address: " + address);
        return new Address();
    }
}
