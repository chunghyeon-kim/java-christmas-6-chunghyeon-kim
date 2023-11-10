package christmas.service;

public class PresentationManager {
    private static final int PRESENTATION_THRESHOLD = 120000;
    private static final int PRESENTATION_PRICE = 25000;

    private int isPresentation(int totalCost) {
        if (totalCost < PRESENTATION_THRESHOLD) {
            return 0;
        }
        return PRESENTATION_PRICE;
    }

}
