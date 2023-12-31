package christmas.domain.constant;

public enum Message {
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    VISIT_DATE_CALL("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENU_CALL("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_ONLY_BEVERAGE("[ERROR] 음료만 주문할 수 없습니다."),
    MENU_UPPER_BOUND_VIOLATION("[ERROR] 메뉴는 20개를 초과할 수 없습니다."),
    WELCOME("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

    private final String content;

    Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
