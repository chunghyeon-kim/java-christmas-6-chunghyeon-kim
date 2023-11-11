package christmas.view;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Badge;
import christmas.domain.constant.Benefit;
import christmas.domain.constant.Message;
import christmas.domain.constant.dish.Beverage;
import christmas.domain.constant.dish.Orderable;
import christmas.domain.dto.BenefitDto;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class OutputView {
    private static final String MENU_ORDERED = "<주문 메뉴>";
    private static final String COST_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private static final String PRESENTATION_MENU = "<증정 메뉴>";
    private static final String BENEFIT_DETAIL = "<혜택 내역>";
    private static final String TOTAL_BENEFIT = "<총혜택 금액>";
    private static final String COST_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private static final String EVENT_BADGE = "<12월 이벤트 배지>";
    private static final DecimalFormat COMMA_EVERY_THREE_DIGIT = new DecimalFormat("#,###");
    private static final String NOTHING = "없음";
    private static final String BLANK = " ";


    public void printWelcomeMessage() {
        System.out.println(Message.WELCOME.getContent());
    }

    public void printEventBenefit(DecemberDate visitDate, BenefitDto benefitDto) {
        printPreviewMessage(visitDate);
        System.out.println(BLANK);
        printMenuOrdered(benefitDto.getDishes());
        System.out.println(BLANK);
        printCostBeforeDiscount(benefitDto);
        System.out.println(BLANK);
        printPresentationMenu(benefitDto);
        System.out.println(BLANK);
        printBenefitDetail(benefitDto);
        System.out.println(BLANK);
        printTotalBenefit(benefitDto);
        System.out.println(BLANK);
        printCostAfterDiscount(benefitDto);
        System.out.println(BLANK);
        printBadge(benefitDto);
    }

    private void printPreviewMessage(DecemberDate visitDate) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", visitDate.date());
    }

    private void printMenuOrdered(Map<Orderable, Integer> dishes) {
        System.out.println(MENU_ORDERED);
        dishes.entrySet().forEach(this::printEachMenu);
    }

    private void printEachMenu(Entry<Orderable, Integer> entry) {
        System.out.printf("%s %d개%n", entry.getKey().getLabel(), entry.getValue());
    }

    private void printCostBeforeDiscount(BenefitDto benefitDto) {
        System.out.println(COST_BEFORE_DISCOUNT);
        System.out.printf("%s원%n", COMMA_EVERY_THREE_DIGIT.format(benefitDto.getTotalCost()));
    }

    private void printPresentationMenu(BenefitDto benefitDto) {
        System.out.println(PRESENTATION_MENU);
        Beverage presentation = benefitDto.getPresentation();
        if (null == presentation) {
            System.out.println(NOTHING);
            return;
        }
        System.out.printf("%s 1개%n", presentation.getLabel());
    }

    private void printBenefitDetail(BenefitDto benefitDto) {
        System.out.println(BENEFIT_DETAIL);
        Set<Entry<Benefit, Integer>> entrySet = benefitDto.getBenefitMap().entrySet();
        if (entrySet.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        entrySet.forEach(this::printEachBenefit);
    }

    private void printEachBenefit(Entry<Benefit, Integer> entry) {
        System.out.printf("%s: -%s원%n", entry.getKey().getLabel(), COMMA_EVERY_THREE_DIGIT.format(entry.getValue()));
    }

    private void printTotalBenefit(BenefitDto benefitDto) {
        System.out.println(TOTAL_BENEFIT);
        System.out.printf("-%s원%n", COMMA_EVERY_THREE_DIGIT.format(benefitDto.getTotalBenefit()));
    }

    private void printCostAfterDiscount(BenefitDto benefitDto) {
        System.out.println(COST_AFTER_DISCOUNT);
        int costAfterDiscount = benefitDto.getTotalCost() - benefitDto.getTotalDiscount();
        System.out.printf("%s원%n", COMMA_EVERY_THREE_DIGIT.format(costAfterDiscount));
    }

    private void printBadge(BenefitDto benefitDto) {
        System.out.println(EVENT_BADGE);
        Badge badge = benefitDto.getBadge();
        if (null == badge) {
            System.out.println(NOTHING);
            return;
        }
        System.out.println(benefitDto.getBadge().getLabel());
    }

}
