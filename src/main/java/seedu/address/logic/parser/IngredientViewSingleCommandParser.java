package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.stream.Stream;

import seedu.address.logic.commands.ingredientcommands.IngredientViewSingleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

/**
 * Parses input arguments and creates a new {@code IngredientViewSingleCommand} object
 */
public class IngredientViewSingleCommandParser implements Parser<IngredientViewSingleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code IngredientViewSingleCommand}
     * and returns a {@code IngredientViewSingleCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public IngredientViewSingleCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_INGREDIENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    IngredientViewSingleCommand.MESSAGE_USAGE));
        }

        Ingredient ingredientToView = ParserUtil.parseIngredient(argMultimap.getValue(PREFIX_INGREDIENT).get());

        IngredientViewSingleCommand.ViewIngredientDescriptor descriptor = new
                IngredientViewSingleCommand.ViewIngredientDescriptor();

        IngredientName ingredientName = ingredientToView.getIngredientName();

        return new IngredientViewSingleCommand(ingredientName, descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

