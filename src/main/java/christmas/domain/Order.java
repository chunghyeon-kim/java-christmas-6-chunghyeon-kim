package christmas.domain;

import christmas.domain.constant.Orderable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final int TOTAL_MENU_UPPER_BOUND = 20;
    private static final String MENU_OVER_BOUND = "[ERROR] 메뉴는 20개를 초과할 수 없습니다.";

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

}
