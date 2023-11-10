package christmas.domain.constant;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private final String label;

    Badge(String label) {
        this.label = label;
    }

}
