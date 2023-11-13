package christmas.service.discountcalculator;

import static christmas.domain.constant.Discount.WEEK_DISCOUNT_UNIT;
import static christmas.domain.constant.Discount.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekDiscountCalculatorTest {
    @DisplayName("주말에 메인 메뉴를 주문하면 그 개수에 따라 주말 할인이 적용된다.")
    @Test
    void applyWeekendDiscountWithMainDish() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(MainDish.BARBEQUE_RIBS, 3);
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 1);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(8);

        WeekDiscountCalculator.apply(menu, benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .containsKey(Benefit.WEEK_END_DISCOUNT);
        assertThat(benefitDto.getBenefitMap())
                .doesNotContainKey(Benefit.WEEK_DAY_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(3 * WEEK_DISCOUNT_UNIT.getValue());
    }

    @DisplayName("주말에 메인 메뉴를 주문하지 않으면 주말 할인이 적용되지 않는다.")
    @Test
    void applyWeekendDiscountWithoutMainDish() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(8);

        WeekDiscountCalculator.apply(menu, benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .doesNotContainKeys(Benefit.WEEK_DAY_DISCOUNT, Benefit.WEEK_END_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(ZERO.getValue());
    }

    @DisplayName("평일에 디저트를 주문하면 그 개수에 따라 평일 할인이 적용된다.")
    @Test
    void applyWeekdayDiscountWithDessert() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(MainDish.BARBEQUE_RIBS, 3);
        menu.put(Dessert.ICE_CREAM, 2);
        menu.put(Dessert.CHOCOLATE_CAKE, 2);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(5);

        WeekDiscountCalculator.apply(menu, benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .containsKey(Benefit.WEEK_DAY_DISCOUNT);
        assertThat(benefitDto.getBenefitMap())
                .doesNotContainKey(Benefit.WEEK_END_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(4 * WEEK_DISCOUNT_UNIT.getValue());
    }

    @DisplayName("평일에 디저트를 주문하지 않으면 평일 할인이 적용되지 않는다.")
    @Test
    void applyWeekdayDiscountWithoutDessert() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(MainDish.BARBEQUE_RIBS, 3);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(5);

        WeekDiscountCalculator.apply(menu, benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .doesNotContainKeys(Benefit.WEEK_DAY_DISCOUNT, Benefit.WEEK_END_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(ZERO.getValue());
    }

}