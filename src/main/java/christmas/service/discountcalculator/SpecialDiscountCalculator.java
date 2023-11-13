package christmas.service.discountcalculator;

import static christmas.domain.constant.Discount.DATE_OF_CHRISTMAS;
import static christmas.domain.constant.Discount.ONE_WEEK;
import static christmas.domain.constant.Discount.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.domain.constant.Discount.THREE;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.dto.BenefitDto;

public class SpecialDiscountCalculator {
    private SpecialDiscountCalculator() {

    }

    public static void applySpecialDiscount(BenefitDto benefitDto, DecemberDate visitDate) {
        if (visitDate.date() == DATE_OF_CHRISTMAS.getValue()
                || visitDate.date() % ONE_WEEK.getValue() == THREE.getValue()) {
            benefitDto.addBenefit(Benefit.SPECIAL_DISCOUNT, SPECIAL_DISCOUNT_AMOUNT.getValue());
        }
    }

}
