package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commandpackages.defaultpackage.executors.commands.ExecuteJTermFile;
import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.commands.commandhandler.CommandHandler;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;
import gelframe.gelfilewindow.geltextpane.GelFile;
import gelterminal.Associations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

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
        if (args.matches(ArgumentType.STRING) != 0)
            throw new CommandHandler.CommandHandlerException("Incorrect arguments given");

        try {
            File f = environment.getDirectory().getFile(args.get(1).value);
            if (f.exists() && f.canWrite() && f.isFile() && f.length() < Integer.MAX_VALUE) {
                gelFrame.loadFile(new GelFile(f));
                environment.addGlobalVariable("fpath", args.get(1).value);
                LinkedList<String> exec = new LinkedList<>();
                exec.add("exec");
                exec.add("C:/gel/default/config");
                new ExecuteJTermFile().parse(
                        input,
                        new CommandArgumentList(exec),
                        environment,
                        output
                );
                for (String s : Associations.getAssociation(f).split("\n")){
                    try {
                        environment.getCommandHandler().handleInput(s);
                    } catch (CommandHandler.CommandHandlerException e){
                        System.out.println("Could not handle command (" + e.getMessage() + ")");
                    }
                }
                environment.removeGlobalVariable("fpath");
            }
        } catch (FileNotFoundException e) {
            throw new CommandHandler.CommandHandlerException(e.getMessage());
        }
    }

}
