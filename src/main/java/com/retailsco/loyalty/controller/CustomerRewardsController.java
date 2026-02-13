package com.retailsco.loyalty.controller;

import com.retailsco.loyalty.dto.RewardsResponse;
import com.retailsco.loyalty.service.CustomerRewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class CustomerRewardsController {
    private final CustomerRewardsService customerRewardsService;

    @GetMapping("/customer/{customerId}")
    public RewardsResponse getCustomerRewards(@PathVariable Long customerId, @RequestParam(required = false, defaultValue = "3") int months) {
        return customerRewardsService.getCustomerRewards(customerId, months);
    }
}
