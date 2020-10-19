package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Set the ingredient to the default levels.
 */
public class SetDefaultCommand extends Command {

    public static final String LINE_SEPARATOR = "\n";
    public static final String COMMAND_WORD = "i-set-default";
    public static final String MESSAGE_SUCCESS = "All ingredients have been set to the default level:" + LINE_SEPARATOR
            + "Milk : 50 L\n" + LINE_SEPARATOR
            + "Pearl : 20 KG\n" + LINE_SEPARATOR
            + "Boba : 20 KG\n" + LINE_SEPARATOR
            + "Oolong Tea : 50 L\n" + LINE_SEPARATOR
            + "Brown Sugar : 20 KG\n" + LINE_SEPARATOR;

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);

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

        model.setIngredientBook(defaultReadOnlyIngredientBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
