package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.dto.BenefitDto;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PresentationManagerTest {
    private static final int PRESENTATION_THRESHOLD = 120000;
    private static final int ONE = 1;

    @DisplayName("할인 전 총주문 금액이 12만원 이상인 경우 샴페인을 증정한다.")
    @Test
    void presentChampagne() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), PRESENTATION_THRESHOLD);
        PresentationManager.present(dto);

        assertThat(dto.getBenefitMap()).containsKey(Benefit.PRESENTATION);
        assertThat(dto.getPresent()).isEqualTo(Beverage.CHAMPAGNE);
        assertThat(dto.getTotalBenefit()).isEqualTo(Beverage.CHAMPAGNE.getPrice());
    }

    @DisplayName("할인 전 총주문 금액이 12만원 미만인 경우 샴페인을 증정하지 않는다.")
    @Test
    void notPresentChampagne() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), PRESENTATION_THRESHOLD - ONE);
        PresentationManager.present(dto);

        assertThat(dto.getBenefitMap()).doesNotContainKey(Benefit.PRESENTATION);
        assertThat(dto.getPresent()).isNull();
        assertThat(dto.getTotalBenefit()).isZero();
    }

}