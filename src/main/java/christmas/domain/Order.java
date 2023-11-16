package christmas.domain;

import christmas.domain.constant.Message;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Orderable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private static final int TOTAL_MENU_UPPER_BOUND = 20;

    private final Map<Orderable, Integer> contents = new LinkedHashMap<>();

    public void addMenu(Orderable menu, int quantity) {
        contents.put(menu, quantity);
    }

    public void validate() {
        validateNotOverLimit();
        validateNotOnlyBeverage();
    }

    private void validateNotOverLimit() {
        int allDishesCount = contents.values().stream()
                .mapToInt(Integer::valueOf)
                .sum();
        if (allDishesCount > TOTAL_MENU_UPPER_BOUND) {
            throw new IllegalArgumentException(Message.MENU_UPPER_BOUND_VIOLATION.getContent());
        }
    }

    private void validateNotOnlyBeverage() {
        if (contents.keySet().stream().allMatch(Beverage.class::isInstance)) {
            throw new IllegalArgumentException(Message.NOT_ONLY_BEVERAGE.getContent());
        }
    }

    public Map<Orderable, Integer> getContents() {
        return contents;
    }

}
