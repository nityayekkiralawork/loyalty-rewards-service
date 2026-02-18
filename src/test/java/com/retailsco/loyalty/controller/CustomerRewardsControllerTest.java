package com.retailsco.loyalty.controller;

import com.retailsco.loyalty.dto.MonthlyReward;
import com.retailsco.loyalty.dto.RewardsResponse;
import com.retailsco.loyalty.service.CustomerRewardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CustomerRewardsControllerTest {

    @Mock
    private CustomerRewardsService customerRewardsService;

    @InjectMocks
    private CustomerRewardsController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // ------------------ SUCCESS : DEFAULT MONTHS ------------------
    @Test
    void shouldReturnRewards_WithDefaultMonths() throws Exception {

        RewardsResponse response = new RewardsResponse(
                101L,
                List.of(
                        new MonthlyReward("December 2025", 50),
                        new MonthlyReward("January 2026", 20),
                        new MonthlyReward("February 2026", 180)
                ),
                250
        );

        when(customerRewardsService.getCustomerRewards(101L, 3))
                .thenReturn(response);

        mockMvc.perform(get("/api/rewards/customer/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(101))
                .andExpect(jsonPath("$.totalPoints").value(250))
                .andExpect(jsonPath("$.monthlyRewards.length()").value(3))
                .andExpect(jsonPath("$.monthlyRewards[0].monthYear").value("December 2025"))
                .andExpect(jsonPath("$.monthlyRewards[2].points").value(180));

        verify(customerRewardsService).getCustomerRewards(101L, 3);
    }

    // ------------------ SUCCESS : CUSTOM MONTHS ------------------
    @Test
    void shouldReturnRewards_WithCustomMonths() throws Exception {

        RewardsResponse response = new RewardsResponse(
                101L,
                List.of(new MonthlyReward("January 2026", 250)),
                250
        );

        when(customerRewardsService.getCustomerRewards(101L, 6))
                .thenReturn(response);

        mockMvc.perform(get("/api/rewards/customer/101")
                        .param("months", "6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyRewards[0].points").value(250));

        verify(customerRewardsService).getCustomerRewards(101L, 6);
    }

    // ------------------ SUCCESS : EMPTY REWARDS ------------------
    @Test
    void shouldReturnEmptyRewards_WhenNoTransactions() throws Exception {

        RewardsResponse response =
                new RewardsResponse(101L, List.of(), 0);

        when(customerRewardsService.getCustomerRewards(101L, 3))
                .thenReturn(response);

        mockMvc.perform(get("/api/rewards/customer/101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyRewards").isEmpty())
                .andExpect(jsonPath("$.totalPoints").value(0));

        verify(customerRewardsService).getCustomerRewards(101L, 3);
    }

    // ------------------ BAD REQUEST : INVALID CUSTOMER ID ------------------
    @Test
    void shouldReturnBadRequest_WhenCustomerIdInvalid() throws Exception {

        mockMvc.perform(get("/api/rewards/customer/abc"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(customerRewardsService);
    }

    // ------------------ BAD REQUEST : INVALID MONTHS ------------------
    @Test
    void shouldReturnBadRequest_WhenMonthsIsInvalid() throws Exception {

        mockMvc.perform(get("/api/rewards/customer/101")
                        .param("months", "abc"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(customerRewardsService);
    }
}
