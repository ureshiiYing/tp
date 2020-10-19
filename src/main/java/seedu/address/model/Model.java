package seedu.address.model;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that evaluates to true if person's archive status is false. */
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_PERSONS = person ->
            !(person.getArchiveStatus().archiveStatus);
    /** {@code Predicate} that evaluates to true if person's archive status is true. */
    Predicate<Person> PREDICATE_SHOW_ALL_ARCHIVED_PERSONS = person -> (
            person.getArchiveStatus().archiveStatus);

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Replaces ingredient book data with the data in {@code ingredientBook}.
     */
    void setIngredientBook(ReadOnlyIngredientBook ingredientBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the IngredientBook
     */
    ReadOnlyIngredientBook getIngredientBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    boolean hasIngredient(Ingredient ingredient);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    void setIngredient(Ingredient target, Ingredient newAmount);

    Ingredient findIngredientByName(IngredientName target);


    void setSalesBook(ReadOnlySalesBook salesBook);

    SalesBook getSalesBook();

    boolean isEmptySalesBook();

    void overwrite(Map<Drink, Integer> salesInput);

    /** Returns an unmodifiable view of the filtered person list */

    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered ingredient list
     */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
}
