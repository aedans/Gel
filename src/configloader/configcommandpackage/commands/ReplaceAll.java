package configloader.configcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelTextPane;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Command for replacing all text in a GelTextPane.
 */

public class ReplaceAll extends Command {

    private GelTextPane gelTextPane;

    public ReplaceAll(GelTextPane gelTextPane) {
        super("replace");
        this.gelTextPane = gelTextPane;
        this.properties[0] = "Replaces all matches with a given replacement.";
        this.properties[1] =
                "replace [string-regex] [string-replacement]:\n" +
                "    Replaces all matches of [string-regex] with [string-replacement].";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        args.checkMatches(ArgumentType.STRING, ArgumentType.STRING);

        gelTextPane.setText(gelTextPane.getText().replaceAll(args.get(1).value, args.get(2).value));
    }

}
