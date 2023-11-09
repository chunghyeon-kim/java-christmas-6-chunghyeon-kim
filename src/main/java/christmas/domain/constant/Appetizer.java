package christmas.domain.constant;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Appetizer implements Orderable {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CEASAR_SALAD("시저샐러드", 8000);

    private static final Map<String, Appetizer> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(Appetizer::getLabel, e -> e));

    private final String label;
    private final int price;

    Appetizer(String label, int price) {
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

    public static Appetizer valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
