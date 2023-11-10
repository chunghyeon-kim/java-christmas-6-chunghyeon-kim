package christmas.domain.dto;

import christmas.domain.constant.Badge;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Orderable;
import java.util.Map;

public class BenefitDto {
    private Map<Orderable, Integer> dishes;
    private int totalCost;
    private Beverage champagne;
    private Map<Benefit, Integer> benefitList;
    private Badge badge;

    public Map<Orderable, Integer> getDishes() {
        return dishes;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public Beverage getChampagne() {
        return champagne;
    }

    public Map<Benefit, Integer> getBenefitList() {
        return benefitList;
    }

    public Badge getBadge() {
        return badge;
    }

}
