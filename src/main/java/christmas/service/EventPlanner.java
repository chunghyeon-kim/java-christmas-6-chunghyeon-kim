package christmas.service;

import christmas.domain.Order;
import christmas.domain.constant.Orderable;
import java.util.Map;

public class EventPlanner {

    public void takeOrder(Map<Orderable, Integer> menu) {
        Order order = new Order();
        menu.forEach(order::addMenu);

        order.isOverLimit();
        order.isOnlyBeverage();
    }
}
