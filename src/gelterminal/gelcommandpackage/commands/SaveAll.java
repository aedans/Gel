package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.commands.commandhandler.CommandHandler;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;

/**
 * Created by Aedan Smith on 10/9/2016.
 *
 * Command for saving all files.
 */

public class SaveAll extends Command {

    private GelFrame gelFrame;

    public SaveAll(GelFrame gelFrame) {
        super("saveall");
        this.gelFrame = gelFrame;
        this.properties[0] = "Saves all active documents.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        gelFrame.saveAll();
    }

}
