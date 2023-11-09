package christmas.domain;

public record DecemberDate(int date) {
    private static final int FIRST_DAY_OF_DECEMBER = 1;
    private static final int LAST_DAY_OF_DECEMBER = 31;
    private static final String WRONG_DATE = "[ERROR] 잘못된 날짜입니다.";

    public DecemberDate {
        if (date < FIRST_DAY_OF_DECEMBER || LAST_DAY_OF_DECEMBER < date) {
            throw new IllegalArgumentException(WRONG_DATE);
        }
    }

}
