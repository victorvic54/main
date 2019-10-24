package seedu.address.logic.quiz.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.quiz.commands.exceptions.CommandException;
import seedu.address.model.quiz.AddressQuizBook;
import seedu.address.model.quiz.Model;
import seedu.address.model.quiz.ReadOnlyAddressBook;

/**
 * Represents a command which can be undone and redone.
 */
public abstract class UndoableCommand extends Command {
    private ReadOnlyAddressBook previousAddressQuizBook;

    protected abstract CommandResult executeUndoableCommand(Model model) throws CommandException;

    /**
     * Stores the current state of {@code model#AddressQuizBook}.
     */
    private void saveAddressBookSnapshot() {
        requireNonNull(model);
        this.previousAddressQuizBook = new AddressQuizBook(model.getAddressBook());
    }

    /**
     * Reverts the AddressQuizBook to the state before this command
     * was executed and updates the filtered person list to
     * show all persons.
     */
    protected final void undo() {
        requireAllNonNull(model, previousAddressQuizBook);
        model.resetData(previousAddressQuizBook);
        model.updateFilteredListToShowAll();
    }

    /**
     * Executes the command and updates the filtered person
     * list to show all persons.
     */
    protected final void redo() {
        requireNonNull(model);
        try {
            executeUndoableCommand();
        } catch (CommandException ce) {
            throw new AssertionError("The command has been successfully executed previously; "
                    + "it should not fail now");
        }
        model.updateFilteredListToShowAll();
    }

    @Override
    public final CommandResult execute() throws CommandException {
        saveAddressBookSnapshot();
        return executeUndoableCommand();
    }
}