package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_ARGUMENT_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindDoctorCommand;
import seedu.address.logic.commands.FindPatientCommand;
import seedu.address.model.doctor.FindDoctorPredicate;

public class FindDoctorCommandParserTest {

    private FindDoctorCommandParser parser = new FindDoctorCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_ARGUMENT_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "Jane123",
                String.format(MESSAGE_INVALID_ARGUMENT_FORMAT, FindDoctorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPatientCommand() {
        // no leading and trailing whitespaces
        FindDoctorCommand expectedFindDoctorCommand =
                new FindDoctorCommand(new FindDoctorPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindDoctorCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindDoctorCommand);
    }
}
