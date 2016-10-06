package configloader.configcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Class for adding stylers.
 */

public class AddStyler extends Command {

    public AddStyler() {
        super("addstyler");
        properties[0] = "Loads a Gel styler file.";
        properties[1] =
                "loadstyler [string-file]:\n" +
                "    Loads the styler file [string-file].styler.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        args.checkMatches(ArgumentType.STRING);

        final String[] content = {""};
        try {
            new BufferedReader(new FileReader(new File(String.valueOf(args.get(1)) + ".styler")))
                    .lines().forEach(s -> content[0] += s + '\n');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (String s : content[0].split("\n")){
            environment.handleInput(input, s, output);
        }

        output.printf("Added styler from file \"%s\"\n", args.get(1).value + ".styler");
    }

}
