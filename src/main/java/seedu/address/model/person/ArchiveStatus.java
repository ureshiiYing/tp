package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's archive status in the address book.
 */
public class ArchiveStatus {
    public static final String MESSAGE_CONSTRAINTS = "Archive status should only be true or false";

    public static final String VALIDATION_REGEX = "^(true|false)$";

    public final boolean archiveStatus;


    /**
     * Constructs a false {@code archiveStatus} of archival if no parameter passed in.
     */
    public ArchiveStatus() {
        this.archiveStatus = false;
    }

    /**
     * Constructs a {@code archiveStatus} based on the archiveStatus passed in.
     *
     * @param archiveStatus A valid archiveStatus of archival.
     */
    public ArchiveStatus(boolean archiveStatus) {
        requireNonNull(archiveStatus);
        this.archiveStatus = archiveStatus;
    }

    /**
     * Returns true if a given string is a valid archiveStatus.
     */
    public static boolean isValidArchiveStatus(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.valueOf(archiveStatus);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ArchiveStatus
                && archiveStatus == ((ArchiveStatus) other).archiveStatus);
    }

    @Override
    public int hashCode() {
        return String.valueOf(archiveStatus).hashCode();
    }
}
