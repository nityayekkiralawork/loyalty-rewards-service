package com.retailsco.loyalty.service;

import com.retailsco.loyalty.dto.MonthlyReward;
import com.retailsco.loyalty.dto.RewardsResponse;
import com.retailsco.loyalty.entity.CustomerTransaction;
import com.retailsco.loyalty.exception.RewardsException;
import com.retailsco.loyalty.repository.CustomerTransactionRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerRewardsServiceImpl implements CustomerRewardsService {
    private final CustomerTransactionRepo customerTransactionRepo;
    private static final Logger log = LoggerFactory.getLogger(CustomerRewardsServiceImpl.class);

    @Override
    public RewardsResponse getCustomerRewards(Long customerId, int months) {
        log.info("CustomerRewardsService getCustomerRewards customerId={} months={}", customerId, months);

        List<CustomerTransaction> rewardsList = customerTransactionRepo.findByCustomerId(customerId);
        if (rewardsList.isEmpty()) {
            throw new RewardsException("No transactions found for customerId=" + customerId);
        }
        LocalDate startDate = LocalDate.now().minusMonths(months);

        Map<String, Integer> montlyRewardsMap = rewardsList.stream()
                .filter(t -> !t.getTransactionDate().isBefore(startDate)).collect(Collectors.groupingBy(t -> {
                            YearMonth ym = YearMonth.from(t.getTransactionDate());
                            return ym.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
                        },

                        Collectors.summingInt(t -> calculatePoints(t.getAmount()))));

        List<MonthlyReward> monthlyRewardList = montlyRewardsMap.entrySet().stream().
                map(entry -> new MonthlyReward(entry.getKey(), entry.getValue())).toList();

        int totalPoints = monthlyRewardList.stream().mapToInt(MonthlyReward::points).sum();
        log.info("CustomerRewardsService rewards calculated customerId={} totalPoints={}", customerId, totalPoints);
        return new RewardsResponse(customerId, monthlyRewardList, totalPoints);

    }

    private int calculatePoints(Double amount) {
        if (amount <= 50) return 0;
        int amt = (int) Math.floor(amount);
        if (amt <= 100) return amt - 50;
        return (amt - 100) * 2 + 50;
    }
}
