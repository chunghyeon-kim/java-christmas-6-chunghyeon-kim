package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DecemberDate;

public class InputView {
    private static final String VISIT_DATE_CALL_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String VISIT_DATE_SHOULD_NOT_CHARACTER = "[ERROR] 방문 날짜는 숫자만 입력할 수 있습니다.";

    public DecemberDate getVisitDate() {
        System.out.println(VISIT_DATE_CALL_MESSAGE);
        DecemberDate visitDate;
        do {
            visitDate = getVisitDateInput();
        } while(visitDate == null);

        return visitDate;
    }

    private DecemberDate getVisitDateInput() {
        try {
            int input = Integer.parseInt(Console.readLine().trim());
            return new DecemberDate(input);
        } catch (NumberFormatException nfe) {
            System.out.println(VISIT_DATE_SHOULD_NOT_CHARACTER);
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
        return null;
    }

}
