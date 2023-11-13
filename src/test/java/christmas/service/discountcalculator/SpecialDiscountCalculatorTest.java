package christmas.service.discountcalculator;

import static christmas.domain.constant.Discount.SPECIAL_DISCOUNT_AMOUNT;
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

class SpecialDiscountCalculatorTest {
    @DisplayName("일요일에 방문하면 특별 할인이 적용된다.")
    @Test
    void applySpecialDiscountSunday() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(10);

        SpecialDiscountCalculator.apply(benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .containsKey(Benefit.SPECIAL_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(SPECIAL_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("크리스마스에 방문하면 특별 할인이 적용된다.")
    @Test
    void applySpecialDiscountChristmas() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(25);

        SpecialDiscountCalculator.apply(benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .containsKey(Benefit.SPECIAL_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(SPECIAL_DISCOUNT_AMOUNT.getValue());
    }

    @DisplayName("방문 날짜가 일요일도 아니고 크리스마스도 아니면 특별 할인이 적용되지 않는다.")
    @Test
    void applySpecialDiscountNotSundayNotChristmas() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Dessert.ICE_CREAM, 1);
        menu.put(Appetizer.TAPAS, 3);
        menu.put(Beverage.ZERO_COLA, 1);

        BenefitDto benefitDto = new BenefitDto(menu, 100000);
        DecemberDate visitDate = new DecemberDate(12);
        SpecialDiscountCalculator.apply(benefitDto, visitDate);

        assertThat(benefitDto.getBenefitMap())
                .doesNotContainKey(Benefit.SPECIAL_DISCOUNT);
        assertThat(benefitDto.getTotalDiscount()).isEqualTo(ZERO.getValue());
    }

}