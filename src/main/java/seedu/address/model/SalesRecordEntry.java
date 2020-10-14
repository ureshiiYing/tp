package seedu.address.model;

import java.util.Objects;


/**
 * Represents a SalesRecordEntry in the sales book.
 */
public class SalesRecordEntry {

    private final Drink drink;
    private final int numberSold;

    public SalesRecordEntry(Drink drink, int numberSold) {
        this.drink = drink;
        this.numberSold = numberSold;
    }

    public Drink getDrink() {
        return drink;
    }

    /**
     * A record entry is the same as another record entry if they record the same Drink item.
     *
     * @param otherEntry the other record entry to compare to
     * @return true if they record the same Drink item, and false otherwise
     */
    public boolean isSameRecord(SalesRecordEntry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        return this.drink.equals(otherEntry.drink);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SalesRecordEntry)) {
            return false;
        }

        SalesRecordEntry otherEntry = (SalesRecordEntry) other;
        return otherEntry.drink.equals(drink)
                && otherEntry.numberSold == numberSold;
    }

    @Override
    public int hashCode() {
        return Objects.hash(drink, numberSold);
    }

}
