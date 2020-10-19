package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.collections.ObservableList;

/**
 * Wraps all data related to drink sales at the sales-book level
 * Fixed Drink types.
 */
public class SalesBook implements ReadOnlySalesBook {

    // private Map<Drink, Integer> record; // TODO: change to List<SalesRecord>
    private UniqueSalesRecordList record;

    public SalesBook() {
        record = new UniqueSalesRecordList();
    }

    /**
     * Creates a SalesBook using the record in {@code toBeCopied}.
     *
     * @param toBeCopied the SalesBook to be copied from
     */
    public SalesBook(ReadOnlySalesBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Resets the existing data of this {@code SalesBook} with {@code newData}.
     */
    public void resetData(ReadOnlySalesBook newData) {
        requireNonNull(newData);
        setRecord(newData.getSalesRecord());
    }

    public void setRecord(List<SalesRecordEntry> sales) {
        requireNonNull(sales);
        record.setSalesRecord(sales);
    }

    /**
     * Sets the sales record to the sales information which is provided as a Map.
     * This is used only at initialisation of the sales record.
     *
     * @param sales sales information that has been parsed.
     */
    public void setRecord(Map<Drink, Integer> sales) {
        requireNonNull(sales);
        record.setSalesRecord(sales);
    }

    public UniqueSalesRecordList getRecord() {
        return record;
    }

    /**
     * Overwrites existing sales record based on the sales information which is provided as a Map.
     * This is used after sales record has been initialised.
     *
     * @param sales sales information that has been parsed.
     */
    public void overwriteSales(Map<Drink, Integer> sales) {
        requireNonNull(sales);
        HashMap<Drink, Integer> newRecord = new HashMap<>();
        // for all the sales items in sales, overwrite them in record
        for (Drink key : sales.keySet()) {
            Optional<Integer> userInput = Optional.ofNullable(sales.get(key));
            Optional<Integer> changedValue = userInput.map(x -> x == 0
                    ? record.getSalesEntry(key).getNumberSold()
                    : sales.get(key));
            newRecord.put(key, changedValue.get());
        }
        record.setSalesRecord(newRecord);
    }

    /**
     * Checks whether the sales record is empty.
     *
     * @return true if the sales record is empty, false otherwise.
     */
    public boolean isEmptySalesRecord() {
        return record.isEmpty();
    }

    //// util methods

    /**
     * Returns an unmodifiable view of the list of sales records.
     * This map will not contain any duplicate drink items.
     */
    @Override
    public ObservableList<SalesRecordEntry> getSalesRecord() {
        return record.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        //return record.toString();
        StringBuilder display = new StringBuilder();

        // record.forEach((k, v) -> display.append(k.getName() + " (" + k + ") : " + v + "\n"));
        record.forEach(x -> display.append(x + "\n"));

        return display.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SalesBook // instanceof handles nulls
                && record.equals(((SalesBook) other).record));
    }

    @Override
    public int hashCode() {
        return record.hashCode();
    }

}
