package com.retailsco.loyalty.repository;

import com.retailsco.loyalty.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for accessing customer transaction data.
 */
@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    /**
     * Returns all transactions for a given customer.
     *
     * @param customerId customer identifier
     * @return list of customer transactions
     */
    List<CustomerTransaction> findByCustomerId(Long customerId);
}
