package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IngredientTest {

    private static final Ingredient OOLONG_TEA = new Ingredient(new IngredientName("Oolong Tea"));
    private static final Ingredient OOLONG_TEA_VALID_AMOUNT = new Ingredient(new IngredientName(
            "Oolong Tea"), new Amount("90"));

    @Test
    public void isSameIngredient() {
        // same object -> returns true
        assertTrue(OOLONG_TEA.isSameIngredient(OOLONG_TEA));

        // null -> returns false
        assertFalse(OOLONG_TEA.isSameIngredient(null));

        // different amount -> returns false
        assertFalse(OOLONG_TEA.isSameIngredient(OOLONG_TEA_VALID_AMOUNT));

        // different name -> returns false
        Ingredient editedOolongTea = new Ingredient(new IngredientName("Boba"));
        assertFalse(OOLONG_TEA.isSameIngredient(editedOolongTea));

    }

    @Test
    public void testEquals() {
        // same values -> returns true
        Ingredient oolongCopy = new Ingredient(new IngredientName("Oolong Tea"));
        assertTrue(OOLONG_TEA.equals(oolongCopy));

        // same object -> returns true
        assertTrue(OOLONG_TEA.equals(OOLONG_TEA));

        // null -> returns false
        assertFalse(OOLONG_TEA.equals(null));

        // different type -> returns false
        assertFalse(OOLONG_TEA.equals(5));

        // different ingredient name -> returns false
        assertFalse(OOLONG_TEA.equals(new Ingredient(new IngredientName("Boba"))));

        // different amount -> returns false
        assertFalse(OOLONG_TEA.equals(OOLONG_TEA_VALID_AMOUNT));

    }
}
