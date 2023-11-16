package christmas.service;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Discount;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import christmas.service.discountcalculator.SpecialDiscountCalculator;
import christmas.service.discountcalculator.WeekDiscountCalculator;
import christmas.service.discountcalculator.XmasDdayDiscountCalculator;
import java.util.Map;

public class DiscountManager {
    public BenefitDto applyDiscount(Map<Orderable, Integer> menus, DecemberDate visitDate) {
        int totalCost = getTotalCost(menus);
        BenefitDto benefitDto = new BenefitDto(menus, totalCost);
        if (totalCost < Discount.DISCOUNT_APPLY_LOWER_BOUND.getValue()) {
            return benefitDto;
        }
        XmasDdayDiscountCalculator.apply(benefitDto, visitDate);
        WeekDiscountCalculator.apply(menus, benefitDto, visitDate);
        SpecialDiscountCalculator.apply(benefitDto, visitDate);
        return benefitDto;
    }

    private int getTotalCost(Map<Orderable, Integer> menus) {
        return menus.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

}
