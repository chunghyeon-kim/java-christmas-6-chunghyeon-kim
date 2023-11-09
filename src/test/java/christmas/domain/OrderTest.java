package christmas.domain;

import christmas.domain.constant.Appetizer;
import christmas.domain.constant.Beverage;
import christmas.domain.constant.Dessert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @DisplayName("총 메뉴 개수가 20을 초과하는 경우 예외가 발생한다.")
    @Test
    void overMenuLimit() {
        order.addMenu(Appetizer.TAPAS, 10);
        order.addMenu(Beverage.ZERO_COLA, 10);
        order.addMenu(Dessert.ICE_CREAM, 1);

        Assertions.assertThatThrownBy(() -> {
            order.isOverLimit();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 메뉴 개수가 20 이하인 경우 예외가 발생하지 않는다.")
    @Test
    void notOverMenuLimit() {
        order.addMenu(Appetizer.TAPAS, 10);
        order.addMenu(Beverage.ZERO_COLA, 10);

        order.isOverLimit();
    }

    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    @Test
    void onlyBeverage() {
        order.addMenu(Beverage.ZERO_COLA, 3);
        order.addMenu(Beverage.RED_WINE, 5);

        Assertions.assertThatThrownBy(() -> {
            order.isOnlyBeverage();
        }).isInstanceOf(IllegalArgumentException.class);
    }

}