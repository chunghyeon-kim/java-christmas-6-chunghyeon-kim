package christmas.domain.constant;

public enum Discount {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    ONE_WEEK(7),
    DATE_OF_CHRISTMAS(25),
    CHRISTMAS_D_DAY_DISCOUNT_UNIT(100),
    CHRISTMAS_D_DAY_DISCOUNT_DEFAULT(1000),
    SPECIAL_DISCOUNT_AMOUNT(1000),
    WEEK_DISCOUNT_UNIT(2023),
    DISCOUNT_APPLY_LOWER_BOUND(10000);

    private final int value;

    Discount(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
