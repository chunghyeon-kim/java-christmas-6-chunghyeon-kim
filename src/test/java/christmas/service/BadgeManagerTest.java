package christmas.service;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.constant.Badge;
import christmas.domain.constant.Benefit;
import christmas.domain.dto.BenefitDto;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeManagerTest {
    private final BadgeManager badgeManager = new BadgeManager();

    @DisplayName("총 혜택금액이 20000원 이상인 경우 산타 배지를 부여한다.")
    @Test
    void grantSantaBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), 100000);
        dto.addBenefit(Benefit.PRESENTATION, 20000);
        badgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isEqualTo(Badge.SANTA);
    }

    @DisplayName("총 혜택금액이 10000원 이상인 경우 산타 배지를 부여한다.")
    @Test
    void grantTreeBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), 100000);
        dto.addBenefit(Benefit.PRESENTATION, 10000);
        badgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isEqualTo(Badge.TREE);
    }

    @DisplayName("총 혜택금액이 5000원 이상인 경우 별 배지를 부여한다.")
    @Test
    void grantStarBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), 100000);
        dto.addBenefit(Benefit.PRESENTATION, 5000);
        badgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isEqualTo(Badge.STAR);
    }

    @DisplayName("총 혜택금액이 5000원 미만인 경우 배지를 부여하지 않는다.")
    @Test
    void notGrantBadge() {
        BenefitDto dto = new BenefitDto(new HashMap<>(), 100000);
        dto.addBenefit(Benefit.PRESENTATION, 4999);
        badgeManager.grantBadge(dto);

        assertThat(dto.getBadge()).isNull();
    }

}