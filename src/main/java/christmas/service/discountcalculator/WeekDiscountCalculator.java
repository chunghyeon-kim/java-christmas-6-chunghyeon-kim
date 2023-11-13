package christmas.service.discountcalculator;

import static christmas.domain.constant.Discount.ONE;
import static christmas.domain.constant.Discount.ONE_WEEK;
import static christmas.domain.constant.Discount.TWO;
import static christmas.domain.constant.Discount.WEEK_DISCOUNT_UNIT;
import static christmas.domain.constant.Discount.ZERO;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import java.util.Map;
import java.util.Map.Entry;

public class WeekDiscountCalculator {
    private WeekDiscountCalculator() {

    }

    public static void applyWeekDiscount(Map<Orderable, Integer> menus, BenefitDto benefitDto, DecemberDate visitDate) {
        if (isWeekend(visitDate)) {
            applyWeekendDiscount(menus, benefitDto);
            return;
        }
        applyWeekdayDiscount(menus, benefitDto);
    }

    private static void applyWeekendDiscount(Map<Orderable, Integer> menus, BenefitDto benefitDto) {
        if (getMainDishCount(menus) == ZERO.getValue()) {
            return;
        }
        benefitDto.addBenefit(Benefit.WEEK_END_DISCOUNT, getMainDishCount(menus) * WEEK_DISCOUNT_UNIT.getValue());
    }

    private static void applyWeekdayDiscount(Map<Orderable, Integer> menus, BenefitDto benefitDto) {
        if (getDessertCount(menus) == ZERO.getValue()) {
            return;
        }
        benefitDto.addBenefit(Benefit.WEEK_DAY_DISCOUNT, getDessertCount(menus) * WEEK_DISCOUNT_UNIT.getValue());
    }

    private static int getDessertCount(Map<Orderable, Integer> menus) {
        return countDishesOfCertainCategory(menus, Dessert.class);
    }

    private static int getMainDishCount(Map<Orderable, Integer> menus) {
        return countDishesOfCertainCategory(menus, MainDish.class);
    }

    private static int countDishesOfCertainCategory(Map<Orderable, Integer> menus,
                                                    Class<? extends Orderable> category) {
        int count = ZERO.getValue();
        for (Entry<Orderable, Integer> entry : menus.entrySet()) {
            if (entry.getKey().getClass() == category) {
                count += entry.getValue();
            }
        }
        return count;
    }

    private static boolean isWeekend(DecemberDate visitDate) {
        int dateNumber = visitDate.date();
        return dateNumber % ONE_WEEK.getValue() == ONE.getValue()
                || dateNumber % ONE_WEEK.getValue() == TWO.getValue();
    }

}
