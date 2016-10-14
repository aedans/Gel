package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.commands.commandhandler.CommandHandler;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

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
        if (args.matches(ArgumentType.STRING) != 0)
            throw new CommandHandler.CommandHandlerException("Incorrect arguments given");

        LoadFile loadFile = new LoadFile(gelFrame);
        File file = environment.getDirectory().getFile(args.get(1).value);
        LinkedList<String> loadf = new LinkedList<>();
        loadf.add("loadfile");
        for (File f : file.listFiles()) {
            loadf.add(f.getAbsolutePath());
            if (!f.isDirectory()){
                loadFile.parse(
                        input,
                        new CommandArgumentList(loadf),
                        environment,
                        output
                );
            } else {
                this.parse(
                        input,
                        new CommandArgumentList(loadf),
                        environment,
                        output
                );
            }
            loadf.removeLast();
        }
    }

}
