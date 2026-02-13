package com.retailsco.loyalty.service;

import com.retailsco.loyalty.dto.RewardsResponse;

public interface CustomerRewardsService {

    RewardsResponse getCustomerRewards(Long customerId, int months);
}
