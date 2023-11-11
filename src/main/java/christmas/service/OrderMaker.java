package christmas.service;

import christmas.domain.Order;
import christmas.domain.constant.Message;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import java.util.Map;

public class OrderMaker {
    public Order makeOrder(Map<String, Integer> parsedOrder) {
        Order order = new Order();
        parsedOrder.forEach((dishLabel, count) -> order.addMenu(findDish(dishLabel), count));
        return order;
    }

    public Orderable findDish(String input) {

        if (null != Appetizer.valueOfLabel(input)) {
            return Appetizer.valueOfLabel(input);
        }
        if (null != Beverage.valueOfLabel(input)) {
            return Beverage.valueOfLabel(input);
        }
        if (null != Dessert.valueOfLabel(input)) {
            return Dessert.valueOfLabel(input);
        }
        if (null != MainDish.valueOfLabel(input)) {
            return MainDish.valueOfLabel(input);
        }

        throw new IllegalArgumentException(Message.INVALID_ORDER.getContent());
    }

}
