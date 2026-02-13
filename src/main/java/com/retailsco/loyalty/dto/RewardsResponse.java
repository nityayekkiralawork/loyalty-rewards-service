package com.retailsco.loyalty.dto;

import java.util.List;

public record RewardsResponse(Long customerId,
                              List<MonthlyReward> monthlyRewards,
                              int totalPoints) {
}
