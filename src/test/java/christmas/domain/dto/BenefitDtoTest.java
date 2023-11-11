package christmas.domain.dto;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Appetizer;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.MainDish;
import christmas.domain.constant.dish.Orderable;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitDtoTest {

    @DisplayName("총 할인금액은 증정이벤트의 혜택이 제외된 가격이다.")
    @Test
    void totalCostNotContainsPresentation() {
        Map<Orderable, Integer> menu = new HashMap<>();
        menu.put(Appetizer.TAPAS, 1);
        menu.put(MainDish.BARBEQUE_RIBS, 1);
        menu.put(Beverage.CHAMPAGNE, 1);

        int totalCost = Appetizer.TAPAS.getPrice()
                + MainDish.BARBEQUE_RIBS.getPrice()
                + Beverage.CHAMPAGNE.getPrice();

        BenefitDto dto = new BenefitDto(menu, totalCost);

        dto.addBenefit(Benefit.D_DAY_DISCOUNT, 2000);
        dto.addBenefit(Benefit.WEEK_END_DISCOUNT, 2023);
        dto.addBenefit(Benefit.SPECIAL_DISCOUNT, 1000);
        dto.addBenefit(Benefit.PRESENTATION, 25000);

        assertThat(dto.getTotalDiscount()).isEqualTo(2000 + 2023 + 1000);
    }

}