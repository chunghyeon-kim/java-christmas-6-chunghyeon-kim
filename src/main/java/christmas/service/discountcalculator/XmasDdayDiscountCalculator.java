package christmas.service.discountcalculator;

import static christmas.domain.constant.Discount.CHRISTMAS_D_DAY_DISCOUNT_DEFAULT;
import static christmas.domain.constant.Discount.CHRISTMAS_D_DAY_DISCOUNT_UNIT;
import static christmas.domain.constant.Discount.DATE_OF_CHRISTMAS;
import static christmas.domain.constant.Discount.ONE;
import static christmas.domain.constant.Discount.ZERO;

import christmas.domain.DecemberDate;
import christmas.domain.constant.Benefit;
import christmas.domain.dto.BenefitDto;

public class XmasDdayDiscountCalculator {
    private XmasDdayDiscountCalculator() {

    }

    public static void apply(BenefitDto benefitDto, DecemberDate visitDate) {
        if (visitDate.date() <= DATE_OF_CHRISTMAS.getValue()) {
            benefitDto.addBenefit(Benefit.D_DAY_DISCOUNT, calculateChristmasDdayDiscount(visitDate));
        }
    }

    private static int calculateChristmasDdayDiscount(DecemberDate visitDate) {
        if (visitDate.date() > DATE_OF_CHRISTMAS.getValue()) {
            return ZERO.getValue();
        }
        return (visitDate.date() - ONE.getValue()) * CHRISTMAS_D_DAY_DISCOUNT_UNIT.getValue()
                + CHRISTMAS_D_DAY_DISCOUNT_DEFAULT.getValue();
    }

}
