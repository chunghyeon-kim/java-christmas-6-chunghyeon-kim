package christmas.domain.dto;

import christmas.domain.constant.Badge;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Orderable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BenefitDto {
    private final Map<Orderable, Integer> dishes;
    private final int totalCost;
    private Beverage presentation;
    private final Map<Benefit, Integer> benefitMap = new HashMap<>();
    private Badge badge;

    public BenefitDto(Map<Orderable, Integer> dishes, int totalCost) {
        this.dishes = dishes;
        this.totalCost = totalCost;
    }

    public void addBenefit(Benefit benefit, Integer value) {
        benefitMap.put(benefit, value);
    }

    public int getTotalDiscount() {
        return benefitMap.entrySet().stream()
                .filter(entry -> entry.getKey() != Benefit.PRESENTATION)
                .map(Entry::getValue)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public Map<Orderable, Integer> getDishes() {
        return dishes;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public Beverage getPresentation() {
        return presentation;
    }

    public Map<Benefit, Integer> getBenefitMap() {
        return benefitMap;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }
}
