package com.retailsco.loyalty.service;

import com.retailsco.loyalty.dto.RewardsResponse;
import com.retailsco.loyalty.entity.CustomerTransaction;
import com.retailsco.loyalty.repository.CustomerTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerRewardsServiceTest {
    @Mock
    CustomerTransactionRepository customerTransactionRepository;
    @InjectMocks
    CustomerRewardsServiceImpl customerRewardsService;

    @BeforeEach
    void setup() {
    }

    @Test
    void test_getCustomerRewards() {
        List<CustomerTransaction> transactionList = List.of(
                new CustomerTransaction(1L, 101L, LocalDate.now().minusDays(10), 120.0),
                new CustomerTransaction(2L, 101L, LocalDate.now().minusMonths(7), 120.0),
                new CustomerTransaction(3L, 101L, LocalDate.now().minusMonths(2), 75.0),
                new CustomerTransaction(4L, 101L, LocalDate.now().minusMonths(1), 100.0),
                new CustomerTransaction(5L, 101L, LocalDate.now().minusMonths(3), 50.0),
                new CustomerTransaction(6L, 101L, LocalDate.now().minusMonths(4), 25.0));
        when(customerTransactionRepository.findByCustomerId(anyLong())).thenReturn(transactionList);
        RewardsResponse response = customerRewardsService.getCustomerRewards(101L, 6);

        assertEquals(165, response.totalPoints());
        assertEquals(5, response.monthlyRewards().size());
        assertEquals(25, response.monthlyRewards().get(4).points());
    }

    @Test
    void test_calculatePoints() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = CustomerRewardsServiceImpl.class.getDeclaredMethod("calculatePoints", Double.class);
        method.setAccessible(true);
        assertEquals(50, (int) method.invoke(customerRewardsService, 100.00));
        assertEquals(25, (int) method.invoke(customerRewardsService, 75.00));
        assertEquals(0, (int) method.invoke(customerRewardsService, 50.00));
        assertEquals(0, (int) method.invoke(customerRewardsService, 25.00));
        assertEquals(90, (int) method.invoke(customerRewardsService, 120.00));
        assertEquals(250, (int) method.invoke(customerRewardsService, 200.00));

    }
}
