package gelterminal.gelcommandpackage.commands.popups;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.commands.commandhandler.CommandHandler;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class RequestText extends Command {

    public RequestText(){
        super("popups.requesttext");
        this.properties[0] = "Requests text with a popup.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        if (args.matches(ArgumentType.STRING) != 0)
            throw new CommandHandler.CommandHandlerException("Incorrect arguments given");
        
        output.println(
                JOptionPane.showInputDialog(
                        new JFrame(),
                        args.get(1).value,
                        null,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                )
        );
    }

}
