package christmas.domain;

import christmas.domain.constant.Beverage;
import christmas.domain.constant.Orderable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final int TOTAL_MENU_UPPER_BOUND = 20;
    private static final String MENU_OVER_BOUND = "[ERROR] 메뉴는 20개를 초과할 수 없습니다.";
    private static final String ONLY_BEVERAGE = "[ERROR] 음료만 주문할 수 없습니다.";

    private final Map<Orderable, Integer> contents = new HashMap<>();

    public void addMenu(Orderable menu, int quantity) {
        contents.put(menu, quantity);
    }

    public void isOverLimit() {
        Collection<Integer> quantities = contents.values();
        AtomicInteger count = new AtomicInteger();
        quantities.forEach(count::addAndGet);
        if (count.get() > TOTAL_MENU_UPPER_BOUND) {
            throw new IllegalArgumentException(MENU_OVER_BOUND);
        }
    }

    public void isOnlyBeverage() {
        Set<Orderable> orderableSet = contents.keySet();
        if (orderableSet.stream().allMatch(Beverage.class::isInstance)) {
            throw new IllegalArgumentException(ONLY_BEVERAGE);
        }
    }

    public Map<Orderable, Integer> getContents() {
        return contents;
    }

}
