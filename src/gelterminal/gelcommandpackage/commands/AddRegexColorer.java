package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;
import gelframe.gelfilewindow.geltextpane.GelTextPane;
import gelframe.gelfilewindow.geltextpane.gelstylers.RegexColorStyler;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Class for adding regex colorers.
 */

public class AddRegexColorer extends Command {

    private GelFrame gelFrame;

    private static Pattern colorPattern = Pattern.compile("(\\d+), *(\\d+), *(\\d+)");

    public AddRegexColorer(GelFrame gelFrame) {
        super("addregexcolorer");
        this.gelFrame = gelFrame;
        properties[0] = "Adds a regex colorer to the GelFrame";
        properties[1] =
                "addregexcolorer [string-color] [string-regex]:\n" +
                "    Loads a regex colorer for color [string-color] with regex [string-regex].";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        args.checkMatches(ArgumentType.STRING, ArgumentType.STRING);

        Matcher m = colorPattern.matcher(args.get(1).value);
        if (m.find())
            gelFrame.getActiveWindow().getGelTextPane().addStyler(new RegexColorStyler(
                args.get(2).value,
                new Color(
                        Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3))
                )
        ));

        output.printf("Added RegexColorer for color \"%s\" with regex \"%s\"\n", args.get(1).value, args.get(2).value);
    }

}
