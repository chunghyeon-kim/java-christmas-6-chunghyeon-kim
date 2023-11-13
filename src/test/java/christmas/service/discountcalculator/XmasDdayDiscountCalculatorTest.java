package christmas.service.discountcalculator;

import static christmas.domain.constant.Discount.CHRISTMAS_D_DAY_DISCOUNT_DEFAULT;
import static christmas.domain.constant.Discount.CHRISTMAS_D_DAY_DISCOUNT_UNIT;
import static christmas.domain.constant.Discount.DATE_OF_CHRISTMAS;
import static christmas.domain.constant.Discount.ONE;
import static christmas.domain.constant.Discount.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Dessert;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XmasDdayDiscountCalculatorTest {
    private static final int EXAMPLE_TOTAL_COST = 100000;

    @DisplayName("크리스마스 이전에 방문하면 크리스마스 디데이 할인이 적용된다.")
    @Test
    void applyXmasDdayDiscountBeforeChristmas() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, EXAMPLE_TOTAL_COST);
        DecemberDate visitDate = new DecemberDate(10);
        XmasDdayDiscountCalculator.apply(benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .containsKey(Benefit.D_DAY_DISCOUNT);
        assertThat(benefitDto.getTotalBenefit()).isEqualTo(calculateChristmasDdayDiscount(visitDate));
    }

    @DisplayName("크리스마스에 방문하면 크리스마스 디데이 할인이 적용된다.")
    @Test
    void applyXmasDdayDiscountChristmas() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, EXAMPLE_TOTAL_COST);
        DecemberDate visitDate = new DecemberDate(25);
        XmasDdayDiscountCalculator.apply(benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .containsKey(Benefit.D_DAY_DISCOUNT);
        assertThat(benefitDto.getTotalBenefit()).isEqualTo(calculateChristmasDdayDiscount(visitDate));
    }

    @DisplayName("크리스마스 이후에 방문하면 크리스마스 디데이 할인이 적용되지 않는다.")
    @Test
    void applyXmasDdayDiscountAfterChristmas() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, EXAMPLE_TOTAL_COST);
        DecemberDate visitDate = new DecemberDate(26);
        XmasDdayDiscountCalculator.apply(benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .doesNotContainKey(Benefit.D_DAY_DISCOUNT);
        assertThat(benefitDto.getTotalBenefit()).isEqualTo(ZERO.getValue());
    }

    private int calculateChristmasDdayDiscount(DecemberDate visitDate) {
        if (visitDate.date() > DATE_OF_CHRISTMAS.getValue()) {
            return ZERO.getValue();
        }
        return (visitDate.date() - ONE.getValue()) * CHRISTMAS_D_DAY_DISCOUNT_UNIT.getValue()
                + CHRISTMAS_D_DAY_DISCOUNT_DEFAULT.getValue();
    }

}