package christmas.service;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DiscountManager {
    private static final int ONE_WEEK = 7;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int DISCOUNT_APPLY_LOWER_BOUND = 10000;
    private static final int WEEK_DISCOUNT_UNIT = 2023;
    private static final int DAY_OF_CHRISTMAS = 25;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_DEFAULT = 1000;
    private static final int CHRISTMAS_D_DAY_DISCOUNT_UNIT = 100;
    private static final int SPECIAL_DISCOUNT = 1000;

    public BenefitDto applyDiscount(Map<Orderable, Integer> menu, DecemberDate visitDate) {
        int totalCost = getTotalCost(menu);
        BenefitDto benefitDto = new BenefitDto(menu, totalCost);
        if (totalCost < DISCOUNT_APPLY_LOWER_BOUND) {
            return benefitDto;
        }

        applyWeekDiscount(menu, benefitDto, visitDate);
        applyDdayDiscount(benefitDto, visitDate);
        applySpecialDiscount(benefitDto, visitDate);

        return benefitDto;
    }

    private void applyWeekDiscount(Map<Orderable, Integer> menu, BenefitDto benefitDto, DecemberDate visitDate) {
        if (isWeekend(visitDate)) {
            applyWeekdayDiscount(menu, benefitDto);
        }
        applyWeekendDiscount(menu, benefitDto);
    }

    private void applyWeekdayDiscount(Map<Orderable, Integer> menu, BenefitDto benefitDto) {
        benefitDto.addBenefit(Benefit.WEEKDAY_DISCOUNT, getMainDishCount(menu) * WEEK_DISCOUNT_UNIT);
    }

    private void applyWeekendDiscount(Map<Orderable, Integer> menu, BenefitDto benefitDto) {
        benefitDto.addBenefit(Benefit.WEEKEND_DISCOUNT, getDessertCount(menu) * WEEK_DISCOUNT_UNIT);
    }

    private void applyDdayDiscount(BenefitDto benefitDto, DecemberDate visitDate) {
        if (visitDate.date() <= DAY_OF_CHRISTMAS) {
            benefitDto.addBenefit(Benefit.D_DAY_DISCOUNT, christmasDdayDiscount(visitDate));
        }
    }

    private void applySpecialDiscount(BenefitDto benefitDto, DecemberDate visitDate) {
        if (visitDate.date() == DAY_OF_CHRISTMAS
                || visitDate.date() % ONE_WEEK == 3) {
            benefitDto.addBenefit(Benefit.SPECIAL_DISCOUNT, SPECIAL_DISCOUNT);
        }
    }

    private int getTotalCost(Map<Orderable, Integer> menu) {
        AtomicInteger totalCost = new AtomicInteger();
        menu.forEach((kindOfMenu, quantity) -> totalCost.addAndGet(kindOfMenu.getPrice() * quantity));
        return totalCost.get();
    }

    private boolean isWeekend(DecemberDate date) {
        int dateNumber = date.date();

        return dateNumber % ONE_WEEK == ONE
                || dateNumber % ONE_WEEK == TWO;
    }

    private int getDessertCount(Map<Orderable, Integer> menu) {
        int dessertCount = ZERO;
        for (Map.Entry<Orderable, Integer> entry : menu.entrySet()) {
            if (entry.getKey() instanceof Dessert) {
                dessertCount += entry.getValue();
            }
        }
        return dessertCount;
    }

    private int getMainDishCount(Map<Orderable, Integer> menu) {
        int dessertCount = ZERO;
        for (Map.Entry<Orderable, Integer> entry : menu.entrySet()) {
            if (entry.getKey() instanceof MainDish) {
                dessertCount += entry.getValue();
            }
        }
        return dessertCount;
    }

    private int christmasDdayDiscount(DecemberDate decemberDate) {
        if (decemberDate.date() > DAY_OF_CHRISTMAS) {
            return ZERO;
        }
        return (decemberDate.date() - ONE) * CHRISTMAS_D_DAY_DISCOUNT_UNIT + CHRISTMAS_D_DAY_DISCOUNT_DEFAULT;
    }

}
