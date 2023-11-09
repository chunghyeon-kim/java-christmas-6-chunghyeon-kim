package christmas.domain.constant;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum MainDish implements Orderable {
    T_BONE_STEAK("티본스테이크", 55000),
    BARBEQUE_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private static final Map<String, MainDish> BY_LABEL =
            Stream.of(MainDish.values()).collect(Collectors.toMap(MainDish::getLabel, e -> e));

    private final String label;
    private final int price;

    MainDish(String label, int price) {
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

    public static MainDish valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
