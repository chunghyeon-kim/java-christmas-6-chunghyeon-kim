package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.Badge;
import christmas.domain.constant.Benefit;
import christmas.domain.dto.BenefitDto;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeManagerTest {
    private static final int SANTA_THRESHOLD = 20000;
    private static final int TREE_THRESHOLD = 10000;
    private static final int STAR_THRESHOLD = 5000;
    private static final int ONE = 1;
    private static final int EXAMPLE_TOTAL_COST = 100000;

    @DisplayName("총 혜택금액이 20000원 이상인 경우 산타 배지를 부여한다.")
    @Test
    void grantSantaBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), EXAMPLE_TOTAL_COST);
        dto.addBenefit(Benefit.PRESENTATION, SANTA_THRESHOLD);
        BadgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isEqualTo(Badge.SANTA);
    }

    @DisplayName("총 혜택금액이 20000원 미만인 경우 산타 배지를 부여하지 않는다.")
    @Test
    void notGrantSantaBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), EXAMPLE_TOTAL_COST);
        dto.addBenefit(Benefit.PRESENTATION, SANTA_THRESHOLD - ONE);
        BadgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isNotEqualTo(Badge.SANTA);
    }

    @DisplayName("총 혜택금액이 10000원 이상인 경우 나무 배지를 부여한다.")
    @Test
    void grantTreeBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), EXAMPLE_TOTAL_COST);
        dto.addBenefit(Benefit.PRESENTATION, TREE_THRESHOLD);
        BadgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isEqualTo(Badge.TREE);
    }

    @DisplayName("총 혜택금액이 10000원 미만인 경우 나무 배지를 부여하지 않는다.")
    @Test
    void notGrantTreeBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), EXAMPLE_TOTAL_COST);
        dto.addBenefit(Benefit.PRESENTATION, TREE_THRESHOLD - ONE);
        BadgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isNotEqualTo(Badge.TREE);
    }

    @DisplayName("총 혜택금액이 5000원 이상인 경우 별 배지를 부여한다.")
    @Test
    void grantStarBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), EXAMPLE_TOTAL_COST);
        dto.addBenefit(Benefit.PRESENTATION, STAR_THRESHOLD);
        BadgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isEqualTo(Badge.STAR);
    }

    @DisplayName("총 혜택금액이 5000원 미만인 경우 배지를 부여하지 않는다.")
    @Test
    void notGrantBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), EXAMPLE_TOTAL_COST);
        dto.addBenefit(Benefit.PRESENTATION, STAR_THRESHOLD - ONE);
        BadgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isNull();
    }

}