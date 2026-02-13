package com.retailsco.loyalty.repository;

import com.retailsco.loyalty.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTransactionRepo extends JpaRepository<CustomerTransaction, Long> {
    List<CustomerTransaction> findByCustomerId(Long customerId);
}
