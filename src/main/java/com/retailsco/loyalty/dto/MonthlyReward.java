package com.retailsco.loyalty.dto;
/**
 * Represents reward points earned in a specific month.
 *
 * @param monthYear month and year (e.g., January 2024)
 * @param points reward points earned in that month
 */
public record MonthlyReward(String monthYear,
                            Integer points) {
}
