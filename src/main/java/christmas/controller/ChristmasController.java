package christmas.controller;

import christmas.domain.DecemberDate;
import christmas.domain.Order;
import christmas.domain.dto.BenefitDto;
import christmas.service.BadgeManager;
import christmas.service.DiscountManager;
import christmas.service.OrderMaker;
import christmas.service.PresentationManager;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Objects;

public class ChristmasController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final OrderMaker orderMaker = new OrderMaker();
    private final DiscountManager discountManager = new DiscountManager();

    public void start() {
        outputView.printWelcomeMessage();
        DecemberDate visitDate = inputView.getVisitDate();
        Order order = getOrder();
        BenefitDto benefitDto = discountManager.applyDiscount(order.getContents(), visitDate);
        PresentationManager.present(benefitDto);
        BadgeManager.grantBadge(benefitDto);
        outputView.printEventBenefit(visitDate, benefitDto);
    }

    private Order getOrder() {
        Order order;
        do {
            order = tryToGetOrder();
        } while (Objects.isNull(order));
        return order;
    }

    private Order tryToGetOrder() {
        Order order;
        Map<String, Integer> parsedInput = inputView.getOrder();
        try {
            order = orderMaker.make(parsedInput);
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
            return null;
        }
        return order;
    }

}
