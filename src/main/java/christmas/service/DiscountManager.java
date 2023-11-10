package christmas.service;

import christmas.domain.DecemberDate;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
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
    private static final int PRESENTATION_THRESHOLD = 120000;
    private static final int PRESENTATION_PRICE = 25000;

    public int applyDiscount(Map<Orderable, Integer> menu, DecemberDate date) {
        int totalCost = getTotalCost(menu);
        if (totalCost < DISCOUNT_APPLY_LOWER_BOUND) {
            return ZERO;
        }
        int totalDiscount = ZERO;

        totalDiscount += weekDiscount(menu, date);
        totalDiscount += christmasDdayDiscount(date);
        totalDiscount += specialDiscount(date);

        return totalDiscount;
    }

    private int getTotalCost(Map<Orderable, Integer> menu) {
        AtomicInteger totalCost = new AtomicInteger();
        menu.forEach((kindOfMenu, quantity) -> totalCost.addAndGet(kindOfMenu.getPrice() * quantity));
        return totalCost.get();
    }

    private int isPresentation(int totalCost) {
        if (totalCost < PRESENTATION_THRESHOLD) {
            return ZERO;
        }
        return PRESENTATION_PRICE;
    }

    private boolean isWeekend(DecemberDate date) {
        int dateNumber = date.date();

        return dateNumber % ONE_WEEK == ONE
                || dateNumber % ONE_WEEK == TWO;
    }

    private int weekDiscount(Map<Orderable, Integer> menu, DecemberDate date) {
        if (isWeekend(date)) {
            return getMainDishCount(menu) * WEEK_DISCOUNT_UNIT;
        }
        return getDessertCount(menu) * WEEK_DISCOUNT_UNIT;
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

    private int specialDiscount(DecemberDate decemberDate) {
        if (decemberDate.date() == DAY_OF_CHRISTMAS
                || decemberDate.date() % ONE_WEEK == 3) {
            return SPECIAL_DISCOUNT;
        }
        return ZERO;
    }

}
