package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commandpackages.defaultpackage.executors.commands.ExecuteJTermFile;
import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;
import gelframe.gelfilewindow.geltextpane.GelFile;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Aedan Smith on 10/9/2016.
 */

public class LoadDirectory extends Command {

    private GelFrame gelFrame;

    public LoadDirectory(GelFrame gelFrame) {
        super("loaddir");
        this.gelFrame = gelFrame;
        properties[0] = "Loads a directory into the GelFrame.";
        properties[1] =
                "loadfile [string-directory]:\n" +
                        "     Loads directory [string-directory] into the GelFrame.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        args.checkMatches(ArgumentType.STRING);

        LoadFile loadFile = new LoadFile(gelFrame);
        File file = environment.getDirectory().getFile(args.get(1).value);
        for (File f : file.listFiles()) {
            loadFile.parse(
                    input,
                    new CommandArgumentList(new String[]{"loadfile", f.getAbsolutePath()}),
                    environment,
                    output
            );
        }
    }

}
