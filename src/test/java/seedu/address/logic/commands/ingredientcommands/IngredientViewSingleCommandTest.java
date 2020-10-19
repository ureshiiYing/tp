package seedu.address.logic.commands.ingredientcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

public class IngredientViewSingleCommandTest {
    @Test
    public void equals() {

        Model expectedModel = new ModelManager();

        IngredientBook defaultIngredientBook = new IngredientBook();
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Milk")),
                new Ingredient(new IngredientName("Milk"), new Amount("50")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Pearl")),
                new Ingredient(new IngredientName("Pearl"), new Amount("20")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Boba")),
                new Ingredient(new IngredientName("Boba"), new Amount("20")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Oolong Tea")),
                new Ingredient(new IngredientName("Oolong Tea"), new Amount("50")));
        defaultIngredientBook.setIngredient(new Ingredient(new IngredientName("Brown Sugar")),
                new Ingredient(new IngredientName("Brown Sugar"), new Amount("20")));

        ReadOnlyIngredientBook defaultReadOnlyIngredientBook = defaultIngredientBook;
        expectedModel.setIngredientBook(defaultReadOnlyIngredientBook);
        IngredientViewSingleCommand.ViewIngredientDescriptor descriptor = new
                IngredientViewSingleCommand.ViewIngredientDescriptor();
        final IngredientViewSingleCommand standardCommand = new
                IngredientViewSingleCommand(new IngredientName("Milk"), descriptor);

        // same values -> returns true
        IngredientViewSingleCommand commandWithSameValues = new
                IngredientViewSingleCommand(new IngredientName("Milk"), descriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different ingredient names -> returns false
        IngredientViewSingleCommand commandWithDifferentValues = new
                IngredientViewSingleCommand(new IngredientName("Boba"), descriptor);
        assertFalse(standardCommand.equals(commandWithDifferentValues));


    }

}
