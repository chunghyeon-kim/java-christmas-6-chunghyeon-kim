package christmas.service;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Dessert;
import christmas.domain.constant.MainDish;
import christmas.domain.constant.Orderable;
import java.util.Map;

public class DiscountManager {
    private static final int ONE_WEEK = 7;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int WEEK_DISCOUNT_UNIT = 2023;

    public void applyDiscount(Map<Orderable, Integer> menu, DecemberDate date) {
        int totalCost = 0;
        int totalDiscount = 0;

        totalDiscount += weekDiscount(menu, date);
    }

    private boolean isWeekend(DecemberDate date) {
        int dateNumber = date.getDate();

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
        int dessertCount = 0;
        for (Map.Entry<Orderable, Integer> entry : menu.entrySet()) {
            if (entry.getKey() instanceof Dessert) {
                dessertCount += entry.getValue();
            }
        }
        return dessertCount;
    }

    private int getMainDishCount(Map<Orderable, Integer> menu) {
        int dessertCount = 0;
        for (Map.Entry<Orderable, Integer> entry : menu.entrySet()) {
            if (entry.getKey() instanceof MainDish) {
                dessertCount += entry.getValue();
            }
        }
        return dessertCount;
    }

}
