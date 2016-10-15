package gelterminal.gelcommandpackage.commands;

import javax.swing.JFrame;
import java.util.LinkedList;
import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.environment.Environment;
import gelterminal.gelcommandpackage.commands.popups.*;

public class PopupCommandPackage implements CommandPackage {
    
    @Override
    public void addCommands(Environment environment) {
        environment.addCommand(new RequestText());
    }
    
}
