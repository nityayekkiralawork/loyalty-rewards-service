package com.retailsco.loyalty.controller;

import com.retailsco.loyalty.dto.RewardsResponse;
import com.retailsco.loyalty.service.CustomerRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for customer rewards APIs.
 */
@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class CustomerRewardsController {
    private final CustomerRewardsService customerRewardsService;

    /**
     * Returns reward details for a given customer.
     *
     * @param customerId customer identifier
     * @param months number of months to calculate rewards for
     * @return RewardsResponse containing monthly rewards and total points
     */
    @GetMapping("/customer/{customerId}")
    public RewardsResponse getCustomerRewards(@PathVariable Long customerId, @RequestParam(required = false, defaultValue = "3") int months) {
        return customerRewardsService.getCustomerRewards(customerId, months);
    }
}
