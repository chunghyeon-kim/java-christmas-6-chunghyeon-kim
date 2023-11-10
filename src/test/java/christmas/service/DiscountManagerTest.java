package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DecemberDate;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountManagerTest {
    private static final int WEEK_DISCOUNT_UNIT = 2023;
    private static final int SPECIAL_DISCOUNT = 1000;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int DAY_OF_CHRISTMAS = 25;
    private static final int D_DAY_DISCOUNT_UNIT = 100;
    private static final int D_DAY_DISCOUNT_DEFAULT = 1000;

    private final DiscountManager discountManager = new DiscountManager();

    @DisplayName("총 주문 금액이 10000원 미만이면 할인이 적용되지 않는다.")
    @Test
    void notApplyDiscountUnder10000() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Appetizer.TAPAS, 1);
        menu.put(Beverage.ZERO_COLA, 1);

        DecemberDate visitDate = new DecemberDate(1);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount()).isZero();
    }

    @DisplayName("디데이 할인, 주말 할인 적용 테스트.")
    @Test
    void applyDateAndWeekendDiscount() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(MainDish.BARBEQUE_RIBS, 1);
        menu.put(Appetizer.TAPAS, 1);
        menu.put(Beverage.ZERO_COLA, 1);

        DecemberDate visitDate = new DecemberDate(8);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount()).isEqualTo(WEEK_DISCOUNT_UNIT + dDayDiscount(visitDate));
    }

    @DisplayName("디데이 할인, 주말 할인 적용 테스트. (메인 메뉴가 없는 경우)")
    @Test
    void applyDateAndWeekendDiscount2() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Appetizer.CEASAR_SALAD, 3);
        menu.put(Appetizer.TAPAS, 1);
        menu.put(Beverage.ZERO_COLA, 1);

        DecemberDate visitDate = new DecemberDate(8);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount()).isEqualTo(dDayDiscount(visitDate));
    }

    @DisplayName("디데이 할인, 평일 할인 적용 테스트.")
    @Test
    void applyDateAndWeekdayDiscount() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 3);
        menu.put(Dessert.CHOCOLATE_CAKE, 1);
        menu.put(MainDish.T_BONE_STEAK, 1);
        menu.put(Beverage.RED_WINE, 1);

        DecemberDate visitDate = new DecemberDate(21);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount()).isEqualTo(4 * WEEK_DISCOUNT_UNIT + dDayDiscount(visitDate));
    }

    @DisplayName("디데이 할인, 평일 할인 적용 테스트. (디저트가 없는 경우)")
    @Test
    void applyDateAndWeekdayDiscount2() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(MainDish.BARBEQUE_RIBS, 2);
        menu.put(MainDish.T_BONE_STEAK, 1);
        menu.put(Beverage.RED_WINE, 1);
        menu.put(Beverage.ZERO_COLA, 2);

        DecemberDate visitDate = new DecemberDate(21);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount()).isEqualTo(dDayDiscount(visitDate));
    }

    @DisplayName("디데이 할인, 평일 할인, 특별 할인 적용 테스트.")
    @Test
    void applyDateAndWeekdayAndSpecialDiscount() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 3);
        menu.put(Dessert.CHOCOLATE_CAKE, 1);
        menu.put(MainDish.T_BONE_STEAK, 1);
        menu.put(Beverage.RED_WINE, 1);

        DecemberDate visitDate = new DecemberDate(25);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount())
                .isEqualTo(4 * WEEK_DISCOUNT_UNIT + dDayDiscount(visitDate) + SPECIAL_DISCOUNT);
    }

    @DisplayName("평일 할인 테스트.")
    @Test
    void applyWeekdayDiscount() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 3);
        menu.put(Dessert.CHOCOLATE_CAKE, 1);

        DecemberDate visitDate = new DecemberDate(27);

        assertThat(discountManager.applyDiscount(menu, visitDate).getTotalDiscount()).isEqualTo(4 * WEEK_DISCOUNT_UNIT);
    }

    private int dDayDiscount(DecemberDate decemberDate) {
        if (decemberDate.date() > DAY_OF_CHRISTMAS) {
            return ZERO;
        }
        return (decemberDate.date() - ONE) * D_DAY_DISCOUNT_UNIT + D_DAY_DISCOUNT_DEFAULT;
    }

}