package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Set the level of one specific ingredient to a specific level.
 */
public class SetCommand extends Command {

    public static final String COMMAND_WORD = "i-set";

    public static final String MESSAGE_ARGUMENTS = "Ingredient- %s, Amount- %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " :set the ingredient in tCheck."
            + "Parameters: "
            + PREFIX_INGREDIENT
            + PREFIX_AMOUNT
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INGREDIENT + "Milk "
            + PREFIX_AMOUNT + "90 ";

    public static final String MESSAGE_SUCCESS = "Ingredient set: %1$s";
    public static final String MESSAGE_NO_CHANGE = "Ingredient level already set.";
    private final Ingredient target;
    private final SetIngredientDescriptor setIngredientDescriptor;

    /**
     * Constructs a set command with the given ingredient name and amount.
     */
    public SetCommand(Ingredient target, SetIngredientDescriptor setIngredientDescriptor) {
        requireNonNull(target);
        requireNonNull(setIngredientDescriptor);

        this.target = target;
        this.setIngredientDescriptor = setIngredientDescriptor;

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        Ingredient ingredientToEdit = lastShownList.get(lastShownList.indexOf(target));
        Ingredient updatedIngredient = createSetIngredient(target, setIngredientDescriptor);

        if (!ingredientToEdit.isSameIngredient(updatedIngredient)
                && model.hasIngredient(updatedIngredient)) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }

        model.setIngredient(ingredientToEdit, updatedIngredient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedIngredient));
    }

    private static Ingredient createSetIngredient(Ingredient target,
                                                  SetIngredientDescriptor setIngredientDescriptor) {
        assert target != null;

        Amount updatedAmount = setIngredientDescriptor.getAmount().orElse(target.getAmount());

        return new Ingredient(target.getIngredientName(), updatedAmount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SetCommand)) {
            return false;
        }

        SetCommand e = (SetCommand) other;

        return target.equals(e.target)
                && setIngredientDescriptor.equals(e.setIngredientDescriptor);
    }

    public static class SetIngredientDescriptor {

        private IngredientName ingredientName;
        private Amount amount;

        public SetIngredientDescriptor(){}

        /**
         * Copy constructor.
         */
        public SetIngredientDescriptor(SetIngredientDescriptor toCopy) {
            setAmount(toCopy.amount);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }
        public Optional<IngredientName> getIngredientName() {
            return Optional.ofNullable(ingredientName);
        }
        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SetCommand.SetIngredientDescriptor)) {
                return false;
            }

            // state check
            SetCommand.SetIngredientDescriptor e = (SetCommand.SetIngredientDescriptor) other;

            return getIngredientName().equals(e.getIngredientName())
                    && getAmount().equals(e.getAmount());
        }
    }
}
