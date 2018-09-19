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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.swing.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @InjectMocks
    private AddressController test;

    private ObjectMapper mapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mapper = new ObjectMapper();
    }

    @Test
    public void testAddAddress() throws Exception {

        Address mockAddress = new Address();
        mockAddress.setAccountId(1L);
        mockAddress.setStreet("Lake Street");
        mockAddress.setCity("New York");
        mockAddress.setCountry("USA");
        mockAddress.setZipcode("76706");

        when(addressService.save(any(Address.class))).thenReturn(mockAddress);

        Address anAddress = new Address();
        anAddress.setAccountId(1L);
        anAddress.setStreet("Lake Street");
        anAddress.setCity("New York");
        anAddress.setCountry("USA");
        anAddress.setZipcode("76706");

        String requestJson = mapper.writeValueAsString(anAddress);

        mockMvc.perform(post("/accounts/{id}/address", anAddress.getAccountId())
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk()).andReturn();


    }

    @Test
    public void testGetAddressByAccountId() throws Exception {

        Address mockAddress = new Address();
        mockAddress.setAccountId(1L);
        mockAddress.setStreet("Lake Street");
        mockAddress.setCity("New York");
        mockAddress.setCountry("USA");
        mockAddress.setZipcode("76706");

        when(addressService.getAddressByAccountId(1L)).thenReturn(mockAddress);

        String requestJson = mapper.writeValueAsString(mockAddress);

        mockMvc.perform(get("/accounts/{id}/address", mockAddress.getAccountId()))
                .andExpect(status().isOk()).andReturn();
    }

}
