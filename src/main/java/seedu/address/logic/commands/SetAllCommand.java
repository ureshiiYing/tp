package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOBA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BROWN_SUGAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MILK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OOLONG_TEA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PEARL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Set all ingredients in the ingredient book to different specified amounts.
 */
public class SetAllCommand extends Command {

    public static final String COMMAND_WORD = "i-set-all";

    public static final String LINE_SEPARATOR = "\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets all ingredients to specified amounts "
            + "in the ingredient book. " + LINE_SEPARATOR
            + "Parameters: " + LINE_SEPARATOR
            + PREFIX_MILK + "Amount_for_Milk "
            + PREFIX_PEARL + "Amount_for_Pearl "
            + PREFIX_BOBA + "Amount_for_Boba "
            + PREFIX_OOLONG_TEA + "Amount_for_Oolong_Tea"
            + PREFIX_BROWN_SUGAR + "Amount_for_Brown_Sugar" + LINE_SEPARATOR
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MILK + "90 "
            + PREFIX_PEARL + "90 "
            + PREFIX_BOBA + "30 "
            + PREFIX_OOLONG_TEA + "40 "
            + PREFIX_BROWN_SUGAR + "10";

    public static final String MESSAGE_NO_CHANGE = "All current amounts have already been "
            + "set to the specified amounts";

    public static final String MESSAGE_SUCCESS = "Ingredient book has been set : %1$s" + LINE_SEPARATOR;

    private final Amount milkAmount;
    private final Amount pearlAmount;
    private final Amount bobaAmount;
    private final Amount oolongTeaAmount;
    private final Amount brownSugarAmount;

    /**
     * Constructs a set all command with the given amounts for all different ingredients.
     */
    public SetAllCommand(Amount milkAmount, Amount pearlAmount, Amount bobaAmount,
                         Amount oolongTeaAmount, Amount brownSugarAmount) {
        requireAllNonNull(milkAmount, pearlAmount, bobaAmount, oolongTeaAmount, brownSugarAmount);
        this.milkAmount = milkAmount;
        this.pearlAmount = pearlAmount;
        this.bobaAmount = bobaAmount;
        this.oolongTeaAmount = oolongTeaAmount;
        this.brownSugarAmount = brownSugarAmount;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isNoChange = model.hasIngredient(new Ingredient(new IngredientName("Milk"), milkAmount))
                && model.hasIngredient(new Ingredient(new IngredientName("Pearl"), pearlAmount))
                && model.hasIngredient(new Ingredient(new IngredientName("Boba"), bobaAmount))
                && model.hasIngredient(new Ingredient(new IngredientName("Oolong Tea"), oolongTeaAmount))
                && model.hasIngredient(new Ingredient(new IngredientName("Brown Sugar"), brownSugarAmount));

        if (isNoChange) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }

        IngredientBook toSet = new IngredientBook();
        toSet.setIngredient(new Ingredient(new IngredientName("Milk")),
                new Ingredient(new IngredientName("Milk"), milkAmount));
        toSet.setIngredient(new Ingredient(new IngredientName("Pearl")),
                new Ingredient(new IngredientName("Pearl"), pearlAmount));
        toSet.setIngredient(new Ingredient(new IngredientName("Boba")),
                new Ingredient(new IngredientName("Boba"), bobaAmount));
        toSet.setIngredient(new Ingredient(new IngredientName("Oolong Tea")),
                new Ingredient(new IngredientName("Oolong Tea"), oolongTeaAmount));
        toSet.setIngredient(new Ingredient(new IngredientName("Brown Sugar")),
                new Ingredient(new IngredientName("Brown Sugar"), milkAmount));

        ReadOnlyIngredientBook readOnlyToSet = toSet;
        model.setIngredientBook(readOnlyToSet);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toSet));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SetAllCommand)) {
            return false;
        }

        SetAllCommand e = (SetAllCommand) other;

        return milkAmount.equals(e.milkAmount)
                && pearlAmount.equals(e.pearlAmount)
                && bobaAmount.equals(e.bobaAmount)
                && oolongTeaAmount.equals(e.oolongTeaAmount)
                && brownSugarAmount.equals(e.brownSugarAmount);
    }

}
