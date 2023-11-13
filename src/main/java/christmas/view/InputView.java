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
    private static final int TWO_WORDS = 2;
    private static final int DISH_COUNT_LOWER_BOUND = 1;

    public DecemberDate getVisitDate() {
        DecemberDate visitDate;
        do {
            System.out.println(Message.VISIT_DATE_CALL.getContent());
            visitDate = getVisitDateInput();
        } while (visitDate == null);

        return visitDate;
    }

    public Map<String, Integer> getOrder() {
        Map<String, Integer> result;
        do {
            result = getOrderInput();
        } while (result.isEmpty());

        return result;
    }

    private Map<String, Integer> getOrderInput() {
        System.out.println(Message.MENU_CALL.getContent());
        String input = Console.readLine().trim();
        if (validateKindAndCountSplit(input)) {
            return new LinkedHashMap<>();
        }
        String[] eachMenuString = splitByComma(input);
        int kindsOfMenu = eachMenuString.length;

        Map<String, Integer> parsedOrder = new LinkedHashMap<>();
        parseOrder(eachMenuString, parsedOrder);
        if (parsedOrder.size() != kindsOfMenu) {
            return new LinkedHashMap<>();
        }
        return parsedOrder;
    }

    private void parseOrder(String[] eachMenuString, Map<String, Integer> parsedOrder) {
        try {
            eachMenuStringToMap(eachMenuString, parsedOrder);
            validateDishDuplicated(eachMenuString.length, parsedOrder);
        } catch (NumberFormatException | IndexOutOfBoundsException nfe) {
            System.out.println(Message.INVALID_ORDER.getContent());
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
    }

    private void eachMenuStringToMap(String[] eachMenuString, Map<String, Integer> parsedOrder) {
        Stream.of(eachMenuString).forEach(kindAndCount -> {
            String[] kindAndCountSplit = splitByHyphen(kindAndCount);
            validateKindAndCountSplit(kindAndCountSplit);
            int dishCount = Integer.parseInt(kindAndCountSplit[SECOND_INDEX]);
            validateDishCount(dishCount);
            parsedOrder.put(kindAndCountSplit[FIRST_INDEX], dishCount);
        });
    }

    private DecemberDate getVisitDateInput() {
        try {
            int input = Integer.parseInt(Console.readLine().trim());
            return new DecemberDate(input);
        } catch (NumberFormatException nfe) {
            System.out.println(Message.INVALID_DATE.getContent());
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
        if (originCount != parsedOrder.keySet().size()) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getContent());
        }
    }

    private boolean validateKindAndCountSplit(String input) {
        boolean inputStartsOrEndWithComma = input.startsWith(COMMA) || input.endsWith(COMMA);
        if (inputStartsOrEndWithComma) {
            System.out.println(Message.INVALID_ORDER.getContent());
        }
        return inputStartsOrEndWithComma;
    }

    private void validateKindAndCountSplit(String[] kindAndCountSplit) {
        if (kindAndCountSplit.length > TWO_WORDS) {
            throw new IllegalArgumentException(Message.INVALID_ORDER.getContent());
        }
    }

}