package christmas.domain.constant.dish;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Beverage implements Orderable {
    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private static final Map<String, Beverage> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(Beverage::getLabel, e -> e));

    private final String label;
    private final int price;

    Beverage(String label, int price) {
        this.label = label;
        this.price = price;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public static Beverage valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
