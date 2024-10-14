package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.doctor.FindDoctorPredicate;


/**
 * Finds and lists all doctors in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindDoctorCommand extends Command {
    public static final String COMMAND_WORD = "find-doctor";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "find-doctor command not implemented yet";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all doctors whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie\n"
            + "Note: KEYWORDS must only contain alphabets and spaces";

    private final FindDoctorPredicate predicate;

    public FindDoctorCommand(FindDoctorPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_DOCTORS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindDoctorCommand)) {
            return false;
        }

        FindDoctorCommand otherFindDoctorCommand = (FindDoctorCommand) other;
        return predicate.equals(otherFindDoctorCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
