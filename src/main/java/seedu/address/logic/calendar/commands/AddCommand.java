package seedu.address.logic.calendar.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.calendar.parser.CliSyntax.PREFIX_TASKDAY;
import static seedu.address.logic.calendar.parser.CliSyntax.PREFIX_TASKDEADLINE;
import static seedu.address.logic.calendar.parser.CliSyntax.PREFIX_TASKDESCRIPTION;
import static seedu.address.logic.calendar.parser.CliSyntax.PREFIX_TASKPLACE;
import static seedu.address.logic.calendar.parser.CliSyntax.PREFIX_TASKTAG;
import static seedu.address.logic.calendar.parser.CliSyntax.PREFIX_TASKTITLE;

import seedu.address.logic.calendar.commands.exceptions.CommandException;
import seedu.address.model.calendar.CalendarModel;
import seedu.address.model.calendar.person.Task;

/**
 * Adds a task to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the address book. "
            + "Parameters: "
            + PREFIX_TASKTITLE + "TITLE "
            + PREFIX_TASKDAY + "TIME "
            + PREFIX_TASKDESCRIPTION + "DESCRIPTION "
            + PREFIX_TASKPLACE + "PLACE "
            + "[" + PREFIX_TASKTAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASKTITLE + "CS2109 Assignment 7 "
            + PREFIX_TASKDAY + "monday "
            + PREFIX_TASKDESCRIPTION + "Submit to Luminus "
            + PREFIX_TASKDEADLINE + "12-10-2019 "
            + PREFIX_TASKPLACE + "NUS "
            + PREFIX_TASKTAG + "IMPORTANT "
            + PREFIX_TASKTAG + "CS2109";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the address book";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(CalendarModel calendarModel) throws CommandException {
        requireNonNull(calendarModel);

        if (calendarModel.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        calendarModel.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
