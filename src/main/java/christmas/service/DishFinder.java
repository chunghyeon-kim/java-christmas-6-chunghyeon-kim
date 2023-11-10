package christmas.service;

import christmas.domain.Order;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import java.util.Map;

public class DishFinder {
    private static final String INVALID_DISH_LABEL = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public Order getOrder(Map<String, Integer> parsedOrder) {
        Order order = new Order();
        parsedOrder.forEach((dishLabel, count) -> order.addMenu(findDish(dishLabel), count));
        return order;
    }

    public Orderable findDish(String input) {

        if (null != findInAppetizer(input)) {
            return findInAppetizer(input);
        }
        if (null != findInBeverage(input)) {
            return findInBeverage(input);
        }
        if (null != findInDessert(input)) {
            return findInDessert(input);
        }
        if (null != findInMainDish(input)) {
            return findInMainDish(input);
        }

        throw new IllegalArgumentException(INVALID_DISH_LABEL);
    }

    private Orderable findInAppetizer(String input) {
        return Appetizer.valueOfLabel(input);
    }

    private Orderable findInBeverage(String input) {
        return Beverage.valueOfLabel(input);
    }

    private Orderable findInDessert(String input) {
        return Dessert.valueOfLabel(input);
    }

    private Orderable findInMainDish(String input) {
        return MainDish.valueOfLabel(input);
    }

}
