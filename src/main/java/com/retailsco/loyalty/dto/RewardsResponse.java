package com.retailsco.loyalty.dto;

import java.util.List;
/**
 * Represents the reward summary for a customer.
 *
 * @param customerId customer identifier
 * @param monthlyRewards list of monthly reward details
 * @param totalPoints total reward points earned
 */
public record RewardsResponse(Long customerId,
                              List<MonthlyReward> monthlyRewards,
                              int totalPoints) {
}
