package christmas.domain.constant;

public enum Benefit {
    D_DAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    PRESENTATION("증정 이벤트");

    private final String label;

    Benefit(String label) {
        this.label = label;
    }

}