package com.finallab.account.AccountUnitTests;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finallab.account.*;
import java.util.ArrayList;
import java.util.List;

import com.finallab.account.controller.AccountController;
import com.finallab.account.model.Account;
import com.finallab.account.model.Address;
import com.finallab.account.service.AccountService;
import com.finallab.account.service.AddressService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountController.class)
@AutoConfigureMockMvc(secure = false)
public class AccountControllerTests {

    @Autowired private MockMvc mockMvc;
    @MockBean private AccountService accountService;
    @MockBean private AddressService addressService;

    private Account account;

    @Before
    public void accountSetUp(){
        account = initializeTestAccount();
    }

    @Test
    public void testGetAccount() throws Exception {
        when(accountService.get(1L)).thenReturn(account);

        mockMvc.perform(post("/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(model().attribute("firstname", "John"))
                .andExpect(model().attribute("lastname", "Doe"))
                .andExpect(model().attribute("email", "johndoe@gmail.com"))
                .andExpect(model().attribute("addressList", hasSize(1)));

        verify(accountService, times(1)).get(1L);
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/account/delete", account)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Account initializeTestAccount(){
        Address address = new Address("Lake Street", "3", "Redmond", "WA",
                "98077", "USA", 1L);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        Account account = new Account("John", "Doe","johndoe@gmail.com", addressList);
        account.setId(1L);

        return account;
    }

}
