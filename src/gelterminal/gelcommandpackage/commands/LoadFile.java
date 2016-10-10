package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commandpackages.defaultpackage.executors.commands.ExecuteJTermFile;
import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import com.aedan.jterminal.utils.FileUtils;
import gelframe.GelFrame;
import gelframe.gelfilewindow.geltextpane.GelFile;
import gelterminal.Associations;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Loads a file to the GelFrame.
 */

public class LoadFile extends Command {

    private GelFrame gelFrame;

    public LoadFile(GelFrame gelFrame) {
        super("loadfile");
        this.gelFrame = gelFrame;
        properties[0] = "Loads a file into the GelFrame.";
        properties[1] =
                "loadfile [string-file]:\n" +
                "     Loads file [string-file] into the GelFrame.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        args.checkMatches(ArgumentType.STRING);



        try {
            File f = environment.getDirectory().getFile(args.get(1).value);
            if (f.exists() && f.canWrite() && f.isFile() && f.length() < Short.MAX_VALUE) {
                gelFrame.loadFile(new GelFile(f));
                new ExecuteJTermFile().parse(
                        input,
                        new CommandArgumentList(new String[]{
                                "exec",
                                Associations.getAssociation(f).getAbsolutePath()
                        }),
                        environment,
                        output
                );
            }
        } catch (FileNotFoundException e) {
            throw new CommandHandler.CommandHandlerException(e.getMessage());
        }
    }

}
