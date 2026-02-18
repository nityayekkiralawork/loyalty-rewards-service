package com.retailsco.loyalty.service;

import com.retailsco.loyalty.dto.RewardsResponse;
/**
 * Service interface for customer reward operations.
 */
public interface CustomerRewardsService {

    /**
     * Returns reward details for a customer.
     *
     * @param customerId customer identifier
     * @param months number of months to calculate rewards for
     * @return RewardsResponse containing reward summary
     */
    RewardsResponse getCustomerRewards(Long customerId, int months);
}
