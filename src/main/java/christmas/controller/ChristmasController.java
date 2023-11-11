package christmas.controller;

import christmas.domain.Order;
import christmas.domain.constant.Message;
import christmas.service.OrderMaker;
import christmas.view.InputView;
import java.util.Map;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OrderMaker orderMaker = new OrderMaker();

    public Order getOrder() {
        Order order;
        do {
            order = tryToGetOrder();
        } while (null == order);
        return order;
    }

    private Order tryToGetOrder() {
        Order order = null;
        Map<String, Integer> parsedInput = inputView.getOrder();
        try {
            order = orderMaker.makeOrder(parsedInput);
        } catch (Exception e) {
            System.out.println(Message.INVALID_ORDER.getContent());
        }
        return order;
    }

}
