package seedu.address.model;

/**
 * Represents the types of drink items available.
 */
public enum Drink {
    BSBM ("Brown Sugar Boba Milk"),
    BSBBT ("Brown Sugar Boba Black Tea"),
    BSBGT ("Brown Sugar Boba Green Tea"),
    BSPM ("Brown Sugar Pearl Milk"),
    BSPBT ("Brown Sugar Pearl Black Tea"),
    BSPGT ("Brown Sugar Pearl Green Tea");

    // TODO: drink ingredients?
    private final String name;

    Drink(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
