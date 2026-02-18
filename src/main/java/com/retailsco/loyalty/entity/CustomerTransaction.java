package com.retailsco.loyalty.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

/**
 * Entity representing a customer transaction.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerTransaction {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identifier of the customer associated with this transaction.
     */
    private Long customerId;

    /**
     * Date on which the transaction occurred.
     */
    private LocalDate transactionDate;

    /**
     * Transaction amount used for reward calculation.
     */
    private Double amount;
}
