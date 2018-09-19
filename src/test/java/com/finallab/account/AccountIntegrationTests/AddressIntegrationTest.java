package com.finallab.account.AccountIntegrationTests;

import com.finallab.account.controller.AddressController;
import com.finallab.account.model.Address;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressIntegrationTest {

    @Autowired
    AddressController addressController;

    @Test
    public void testAddAddress(){
        Address anAddress = new Address();
        anAddress.setStreet("Long Street");
        anAddress.setCity("New York");
        anAddress.setCountry("USA");

        Address result = addressController.saveAddress(anAddress, 1L);

        assertThat(result, Matchers.is(Matchers.equalTo(anAddress)));
    }

}
