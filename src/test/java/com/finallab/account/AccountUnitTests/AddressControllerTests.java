package com.finallab.account.AccountUnitTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finallab.account.controller.AddressController;
import com.finallab.account.model.Address;
import com.finallab.account.repository.AddressRepository;
import com.finallab.account.service.AddressService;
import com.finallab.account.summary.OrderSummary;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.swing.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AddressController.class, secure = false)
public class AddressControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @InjectMocks
    private AddressController test;

    private ObjectMapper mapper;
    private Address address;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
        address = createMockAddress();
    }

    @Test
    public void testAddAddress() throws Exception {

        when(addressService.save(any(Address.class), any(long.class))).thenReturn(address);

        String requestJson = mapper.writeValueAsString(address);

        mockMvc.perform(post("/accounts/{id}/address", address.getAccountId())
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void testGetAddressByAccountId() throws Exception {

        when(addressService.getAddressByAccountId(1L)).thenReturn(address);

        String requestJson = mapper.writeValueAsString(address);

        mockMvc.perform(get("/accounts/{id}/address", address.getAccountId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("street", "Lake Street"))
                .andExpect(model().attribute("city", "Redmond"))
                .andExpect(model().attribute("accountId", 1L))
                .andReturn();

        Mockito.verify(addressService, times(1)).getAddressByAccountId(1L);
    }

    public Address createMockAddress(){
        Address address = new Address("Lake Street", "3", "Redmond", "WA",
                "98077", "USA", 1L);

        return address;

    }

}
