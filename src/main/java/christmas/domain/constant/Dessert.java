package christmas.domain.constant;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Dessert implements Orderable {
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    private static final Map<String, Dessert> BY_LABEL =
            Stream.of(Dessert.values()).collect(Collectors.toMap(Dessert::getLabel, e -> e));

    private final String label;
    private final int price;

    Dessert(String label, int price) {
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

    public static Dessert valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
