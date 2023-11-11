package christmas.service;

import christmas.domain.constant.Benefit;
import christmas.domain.dto.BenefitDto;

public class PresentationManager {
    private static final int PRESENTATION_THRESHOLD = 120000;
    private static final int PRESENTATION_PRICE = 25000;

    private PresentationManager() {

    }

    public static BenefitDto present(BenefitDto benefitDto) {
        if (benefitDto.getTotalCost() >= PRESENTATION_THRESHOLD) {
            benefitDto.addBenefit(Benefit.PRESENTATION, PRESENTATION_PRICE);
            benefitDto.presentChampagne();
        }
        return benefitDto;
    }

}
