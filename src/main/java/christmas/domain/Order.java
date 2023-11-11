package christmas.domain;

import christmas.domain.constant.Message;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Orderable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Order {
    private static final int TOTAL_MENU_UPPER_BOUND = 20;

    private final Map<Orderable, Integer> contents = new LinkedHashMap<>();

    public void addMenu(Orderable menu, int quantity) {
        contents.put(menu, quantity);
    }

    public void isOverLimit() {
        int allDishes = contents.values().stream()
                .mapToInt(Integer::valueOf)
                .sum();
        if (allDishes > TOTAL_MENU_UPPER_BOUND) {
            throw new IllegalArgumentException(Message.MENU_UPPER_BOUND_VIOLATION.getContent());
        }
    }

    public void isOnlyBeverage() {
        Set<Orderable> orderableSet = contents.keySet();
        if (orderableSet.stream().allMatch(Beverage.class::isInstance)) {
            throw new IllegalArgumentException(Message.NOT_ONLY_BEVERAGE.getContent());
        }
    }

    public Map<Orderable, Integer> getContents() {
        return contents;
    }

}
