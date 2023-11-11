package christmas.service;

import christmas.domain.constant.Badge;
import christmas.domain.dto.BenefitDto;

public class BadgeManager {
    private static final int SANTA_THRESHOLD = 20000;
    private static final int TREE_THRESHOLD = 10000;
    private static final int STAR_THRESHOLD = 5000;

    private BadgeManager() {

    }

    public static void grantBadge(BenefitDto benefitDto) {
        if (benefitDto.getTotalBenefit() >= SANTA_THRESHOLD) {
            benefitDto.setBadge(Badge.SANTA);
            return;
        }

        if (benefitDto.getTotalBenefit() >= TREE_THRESHOLD) {
            benefitDto.setBadge(Badge.TREE);
            return;
        }

        if (benefitDto.getTotalBenefit() >= STAR_THRESHOLD) {
            benefitDto.setBadge(Badge.STAR);
        }
    }

}
