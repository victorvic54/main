package seedu.address.logic.calendar.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.calendar.commands.GoCommand;
import seedu.address.logic.calendar.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GoCommand object
 */
public class GoCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoCommand parse(String args) throws ParseException {
        try {
            int week = ParserUtil.parseWeek(args);
            return new GoCommand(week);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
    }
}
