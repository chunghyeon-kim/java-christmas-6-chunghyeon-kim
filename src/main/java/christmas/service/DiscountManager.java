package christmas.service;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class DiscountManager {
    private static final int ONE_WEEK = 7;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int DISCOUNT_APPLY_LOWER_BOUND = 10000;
    private static final int WEEK_DISCOUNT_UNIT = 2023;
    private static final int DATE_OF_CHRISTMAS = 25;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_DEFAULT = 1000;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_UNIT = 100;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    public BenefitDto applyDiscount(Map<Orderable, Integer> menus, DecemberDate visitDate) {
        int totalCost = getTotalCost(menus);
        BenefitDto benefitDto = new BenefitDto(menus, totalCost);
        if (totalCost < DISCOUNT_APPLY_LOWER_BOUND) {
            return benefitDto;
        }
        applyChristmasDdayDiscount(benefitDto, visitDate);
        applyWeekDiscount(menus, benefitDto, visitDate);
        applySpecialDiscount(benefitDto, visitDate);
        return benefitDto;
    }

    private int getTotalCost(Map<Orderable, Integer> menus) {
        AtomicInteger totalCost = new AtomicInteger();
        menus.forEach((kindOfMenu, quantity) -> totalCost.addAndGet(kindOfMenu.getPrice() * quantity));
        return totalCost.get();
    }

    private void applyWeekDiscount(Map<Orderable, Integer> menus, BenefitDto benefitDto, DecemberDate visitDate) {
        if (isWeekend(visitDate)) {
            applyWeekendDiscount(menus, benefitDto);
            return;
        }
        applyWeekdayDiscount(menus, benefitDto);
    }

    private void applyWeekendDiscount(Map<Orderable, Integer> menus, BenefitDto benefitDto) {
        if (getMainDishCount(menus) == ZERO) {
            return;
        }
        benefitDto.addBenefit(Benefit.WEEK_END_DISCOUNT, getMainDishCount(menus) * WEEK_DISCOUNT_UNIT);
    }

    private void applyWeekdayDiscount(Map<Orderable, Integer> menus, BenefitDto benefitDto) {
        if (getDessertCount(menus) == ZERO) {
            return;
        }
        benefitDto.addBenefit(Benefit.WEEK_DAY_DISCOUNT, getDessertCount(menus) * WEEK_DISCOUNT_UNIT);
    }

    private void applyChristmasDdayDiscount(BenefitDto benefitDto, DecemberDate visitDate) {
        if (visitDate.date() <= DATE_OF_CHRISTMAS) {
            benefitDto.addBenefit(Benefit.D_DAY_DISCOUNT, calculateChristmasDdayDiscount(visitDate));
        }
    }

    private void applySpecialDiscount(BenefitDto benefitDto, DecemberDate visitDate) {
        if (visitDate.date() == DATE_OF_CHRISTMAS
                || visitDate.date() % ONE_WEEK == THREE) {
            benefitDto.addBenefit(Benefit.SPECIAL_DISCOUNT, SPECIAL_DISCOUNT_AMOUNT);
        }
    }

    private int getDessertCount(Map<Orderable, Integer> menus) {
        return countDishesOfCertainCategory(menus, Dessert.class);
    }

    private int getMainDishCount(Map<Orderable, Integer> menus) {
        return countDishesOfCertainCategory(menus, MainDish.class);
    }

    private int countDishesOfCertainCategory(Map<Orderable, Integer> menus, Class<? extends Orderable> category) {
        int count = ZERO;
        for (Entry<Orderable, Integer> entry : menus.entrySet()) {
            if (entry.getKey().getClass() == category) {
                count += entry.getValue();
            }
        }
        return count;
    }

    private boolean isWeekend(DecemberDate visitDate) {
        int dateNumber = visitDate.date();
        return dateNumber % ONE_WEEK == ONE
                || dateNumber % ONE_WEEK == TWO;
    }

    private int calculateChristmasDdayDiscount(DecemberDate visitDate) {
        if (visitDate.date() > DATE_OF_CHRISTMAS) {
            return ZERO;
        }
        return (visitDate.date() - ONE) * CHRISTMAS_D_DAY_DISCOUNT_UNIT + CHRISTMAS_D_DAY_DISCOUNT_DEFAULT;
    }

}
