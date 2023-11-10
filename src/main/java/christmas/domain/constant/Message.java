package christmas.domain.constant;

public enum Message {
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    VISIT_DATE_CALL("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENU_CALL("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    VISIT_DATE_SHOULD_NOT_CHARACTER("[ERROR] 방문 날짜는 숫자만 입력할 수 있습니다."),
    MENU_COUNT_SHOULD_NOT_CHARACTER("[ERROR] 메뉴 개수는 숫자만 입력할 수 있습니다.");

    private final String content;

    Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
