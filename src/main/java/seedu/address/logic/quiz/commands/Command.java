package seedu.address.logic.quiz.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.quiz.UndoRedoStack;
import seedu.address.logic.quiz.commands.exceptions.CommandException;
import seedu.address.model.quiz.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    protected Model model;
    protected CommandHistory history;
    protected UndoRedoStack undoRedoStack;

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param displaySize used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(int displaySize) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, displaySize);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

    /**
     * Provides any needed dependencies to the command.
     * Commands making use of any of these should override this method to gain
     * access to the dependencies.
     */
    public void setData(Model model, CommandHistory history, UndoRedoStack undoRedoStack) {
        this.model = model;
    }
}
