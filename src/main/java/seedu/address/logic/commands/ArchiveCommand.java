package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ArchiveStatus;
import seedu.address.model.person.Person;

/**
 * Archives a person identified using it's displayed index from the address book.
 */
public class ArchiveCommand extends Command {

    public static final String COMMAND_WORD = "c-archive";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Archives the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_ARCHIVE_PERSON_SUCCESS = "Archived Person: %1$s";
    public static final String MESSAGE_PERSON_ALREADY_ARCHIVED = "This person has already been archived!";

    private final Index targetIndex;

    /**
     * Constructs an ArchiveCommand.
     *
     * @param targetIndex the index number shown in the displayed person list.
     */
    public ArchiveCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();


        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToArchive = lastShownList.get(targetIndex.getZeroBased());
        ArchiveStatus currentStateOfPerson = personToArchive.getArchiveStatus();

        if (currentStateOfPerson.archiveStatus) {
            throw new CommandException(String.format(MESSAGE_PERSON_ALREADY_ARCHIVED,
                    personToArchive));
        }

        Person archivedPerson = personToArchive.archive();
        model.setPerson(personToArchive, archivedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_ACTIVE_PERSONS);
        return new CommandResult(String.format(MESSAGE_ARCHIVE_PERSON_SUCCESS, personToArchive.getName()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ArchiveCommand // instanceof handles nulls
                && targetIndex.equals(((ArchiveCommand) other).targetIndex)); // state check
    }
}
