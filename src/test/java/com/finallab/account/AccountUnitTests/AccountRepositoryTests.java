package com.finallab.account.AccountUnitTests;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.finallab.account.*;
import com.finallab.account.model.Account;
import com.finallab.account.repository.AccountRepository;
import org.hamcrest.Matchers;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testFindById(){
        Account testAccount = new Account();
        entityManager.persistAndFlush(testAccount);

        Account foundAccount = accountRepository.findById(1L);

        assertThat(foundAccount.getId(), Matchers.is(Matchers.equalTo(Long.valueOf(1))));
    }


}
