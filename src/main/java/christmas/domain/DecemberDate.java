package christmas.domain;

import christmas.domain.constant.Message;

public record DecemberDate(int date) {
    private static final int FIRST_DAY_OF_DECEMBER = 1;
    private static final int LAST_DAY_OF_DECEMBER = 31;

    public DecemberDate {
        if (date < FIRST_DAY_OF_DECEMBER || LAST_DAY_OF_DECEMBER < date) {
            throw new IllegalArgumentException(Message.INVALID_DATE.getContent());
        }
    }

}
