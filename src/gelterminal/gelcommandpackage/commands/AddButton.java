package gelterminal.gelcommandpackage.commands;

import com.aedan.jterminal.commands.Command;
import com.aedan.jterminal.commands.CommandHandler;
import com.aedan.jterminal.commands.commandarguments.ArgumentType;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelframe.gelmenu.GelMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Aedan Smith on 10/6/2016.
 */

public class AddButton extends Command {

    private GelMenu gelMenu;

    public AddButton(GelMenu gelMenu) {
        super("addbutton");
        this.gelMenu = gelMenu;
        this.properties[0] = "Adds a button to the Gel menu that executes a given command.";
        this.properties[1] =
                "addbutton [string-name] [string-command]:\n" +
                "    Adds a button with name [string-name] that executes command [string-command] on click.";
    }

    @Override
    public void parse(CommandInput input, CommandArgumentList args, Environment environment, CommandOutput output)
            throws CommandHandler.CommandHandlerException {
        args.checkMatches(ArgumentType.STRING, ArgumentType.STRING);

        JButton jButton = new JButton();
        jButton.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    environment.handleInput(input, args.get(2).value, output);
                } catch (CommandHandler.CommandHandlerException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        jButton.setText(args.get(1).value);
        gelMenu.add(jButton);

        output.printf("Added button \"%s\" with Action \"%s\"\n", args.get(1).value, args.get(2).value);
    }

}
