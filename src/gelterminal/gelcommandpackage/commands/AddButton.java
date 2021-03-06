package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.commands.commandhandler.CommandHandler;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.GelFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Aedan Smith on 10/6/2016.
 */

public class AddButton extends Command {

    private GelFrame gelFrame;

    public AddButton(GelFrame gelFrame) {
        super("addbutton");
        this.gelFrame = gelFrame;
        this.properties[0] = "Adds a button to the Gel menu that executes a given command.";
        this.properties[1] =
                "addbutton [string-name] [string-command]:\n" +
                "    Adds a button with name [string-name] that executes command [string-command] on click.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        if (args.matches(ArgumentType.STRING, ArgumentType.STRING) != 0)
            throw new CommandHandler.CommandHandlerException("Incorrect arguments given");

        JButton jButton = new JButton();
        jButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    environment.getCommandHandler().handleInput(args.get(2).value);
                } catch (CommandHandler.CommandHandlerException n) {
                    System.out.println(n.getMessage());
                }
            }
        });
        jButton.setText(args.get(1).value);
        gelFrame.getActiveWindow().getGelMenu().add(jButton);
    }

}
