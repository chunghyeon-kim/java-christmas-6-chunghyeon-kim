package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.DecemberDate;
import christmas.domain.constant.Message;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class InputView {
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int DISH_COUNT_LOWER_BOUND = 1;

    public DecemberDate getVisitDate() {
        System.out.println(Message.VISIT_DATE_CALL.getContent());
        DecemberDate visitDate;
        do {
            visitDate = getVisitDateInput();
        } while (visitDate == null);

        return visitDate;
    }

    public Map<String, Integer> getOrder() {
        Map<String, Integer> result;
        do {
            result = getOrderInput();
        } while(result.isEmpty());

        return result;
    }

    private Map<String, Integer> getOrderInput() {
        System.out.println(Message.MENU_CALL.getContent());
        String input = Console.readLine().trim();
        String[] eachMenuString = splitByComma(input);
        int kindOfMenu = eachMenuString.length;

        Map<String, Integer> parsedOrder = new LinkedHashMap<>();

        try {
            Stream.of(eachMenuString).forEach(string -> {
                String[] menuAndCount = splitByHyphen(string);
                int dishCount = Integer.parseInt(menuAndCount[SECOND_INDEX]);
                validateDishCount(dishCount);
                parsedOrder.put(menuAndCount[FIRST_INDEX], dishCount);
                validateDishDuplicated(menuAndCount.length, parsedOrder);
            });
        } catch (NumberFormatException nfe) {
            System.out.println(Message.INVALID_ORDER.getContent());
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        } catch (IndexOutOfBoundsException ioe) {
            System.out.println(Message.INVALID_ORDER.getContent());
        }

        if (parsedOrder.size() != kindOfMenu) {
            return new LinkedHashMap<>();
        }

        return parsedOrder;
    }

    private DecemberDate getVisitDateInput() {
        try {
            int input = Integer.parseInt(Console.readLine().trim());
            return new DecemberDate(input);
        } catch (NumberFormatException nfe) {
            System.out.println(Message.VISIT_DATE_SHOULD_NOT_CHARACTER.getContent());
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
        return null;
    }

    private String[] splitByComma(String input) {
        return input.split(COMMA);
    }

    private String[] splitByHyphen(String input) {
        return input.split(HYPHEN);
    }

    private void validateDishCount(int dishCount) {
        if (dishCount < DISH_COUNT_LOWER_BOUND) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getContent());
        }
    }

    private void validateDishDuplicated(int originCount, Map<String, Integer> parsedOrder) {
        if (originCount != parsedOrder.size()) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getContent());
        }
    }

}