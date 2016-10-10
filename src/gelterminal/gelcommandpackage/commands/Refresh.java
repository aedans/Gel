package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;
import gelframe.gelfilewindow.GelFileWindow;

/**
 * Created by Aedan Smith on 10/10/2016.
 *
 * Command for refreshing GelFrames.
 */

public class Refresh extends Command {

    private GelFrame gelFrame;

    public Refresh(GelFrame gelFrame) {
        super("refresh");
        this.gelFrame = gelFrame;
        properties[0] = "Refreshes the GelFrame.";
        properties[1] = "refresh:\n    Refreshes the GelFrame.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        for (GelFileWindow gelFileWindow : gelFrame.getGelFileWindows()) {
            gelFileWindow.getGelTextPane().refresh();
        }
    }

}
