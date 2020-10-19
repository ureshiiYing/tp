package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SalesBookTest {

    private final SalesBook salesBook = new SalesBook();
    private final HashMap<Drink, Integer> sales = new HashMap<>();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), salesBook.getSalesRecord());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySalesBook_replacesData() {
        SalesBook newData = new SalesBook();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        newData.setRecord(salesRecordEntryList);

        salesBook.resetData(newData);
        assertEquals(newData, salesBook);
    }

    @Test
    public void setRecord_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.setRecord((List<SalesRecordEntry>) null));
    }

    @Test
    public void setRecord_list_replacesOwnUniqueListWithProvidedList() {
        SalesBook newData = new SalesBook();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        newData.setRecord(salesRecordEntryList);

        assertEquals(salesRecordEntryList, newData.getSalesRecord());
    }

    @Test
    public void setRecord_nullMap_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.setRecord((Map<Drink, Integer>) null));
    }

    @Test
    public void setRecord_mapWithAllDrinksInitialised_success() {
        sales.put(Drink.BSBM, 80);
        sales.put(Drink.BSBBT, 20);
        sales.put(Drink.BSBGT, 0);
        sales.put(Drink.BSPM, 0);
        sales.put(Drink.BSPBT, 0);
        sales.put(Drink.BSPGT, 0);

        salesBook.setRecord(sales);
        assertEquals(Drink.values().length, salesBook.getRecord().size());
    }

    @Test
    public void overwriteSales_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> salesBook.overwriteSales(null));
    }

    @Test
    public void overwriteSales_withOneDrinkItems_success() {
        setRecord_mapWithAllDrinksInitialised_success();

        sales.put(Drink.BSBM, 90);
        salesBook.overwriteSales(sales);

        UniqueSalesRecordList expectedSalesRecord = new UniqueSalesRecordList();
        expectedSalesRecord.setSalesRecord(sales);

        assertEquals(expectedSalesRecord, salesBook.getRecord());
    }

    @Test
    void isEmptySalesBook() {
        assertTrue(salesBook.isEmptySalesRecord());
    }

    @Test
    public void getSalesRecordList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> salesBook.getSalesRecord().remove(0));
    }

    @Test
    public void equals() {
        SalesBook sameBook = new SalesBook();
        SalesRecordEntry newEntry = new SalesRecordEntry(Drink.BSBGT, 10);
        List<SalesRecordEntry> salesRecordEntryList = Collections.singletonList(newEntry);
        sameBook.setRecord(salesRecordEntryList);

        salesBook.setRecord(salesRecordEntryList);

        SalesBook differentBook = new SalesBook();
        sales.put(Drink.BSPGT, 20);
        differentBook.setRecord(sales);

        // same values -> returns true
        assertTrue(salesBook.equals(sameBook));

        // same object -> returns true
        assertTrue(salesBook.equals(salesBook));

        // null -> returns false
        assertFalse(salesBook.equals(null));

        // different types -> returns false
        assertFalse(salesBook.equals(sales));

        // different salesBook -> returns false
        assertFalse(salesBook.equals(differentBook));
    }
}
