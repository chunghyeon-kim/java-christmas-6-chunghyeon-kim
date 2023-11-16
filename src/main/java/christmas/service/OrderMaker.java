package christmas.service;

import christmas.domain.Order;
import christmas.domain.constant.Message;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import java.util.Map;
import java.util.Objects;

public class OrderMaker {
    public Order make(Map<String, Integer> parsedOrder) {
        Order order = new Order();
        parsedOrder.forEach((dishLabel, count) -> order.addMenu(findDish(dishLabel), count));
        order.validate();
        return order;
    }

    private Orderable findDish(String input) {
        if (Objects.nonNull(Appetizer.valueOfLabel(input))) {
            return Appetizer.valueOfLabel(input);
        }
        if (Objects.nonNull(Beverage.valueOfLabel(input))) {
            return Beverage.valueOfLabel(input);
        }
        if (Objects.nonNull(Dessert.valueOfLabel(input))) {
            return Dessert.valueOfLabel(input);
        }
        if (Objects.nonNull(MainDish.valueOfLabel(input))) {
            return MainDish.valueOfLabel(input);
        }
        throw new IllegalArgumentException(Message.INVALID_ORDER.getContent());
    }

}
