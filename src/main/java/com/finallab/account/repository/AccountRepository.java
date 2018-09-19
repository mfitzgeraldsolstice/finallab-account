package com.finallab.account.repository;

import com.finallab.account.model.Account;
import com.finallab.account.summary.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findById(long id);

    @Query(nativeQuery = true)
    List<OrderSummary> findAllOrders(@Param("id") Long id);
}
