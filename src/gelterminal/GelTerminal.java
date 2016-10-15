package gelterminal;

import com.aedan.jterminal.JTerminal;
import com.aedan.jterminal.commandpackages.defaultpackage.DefaultPackage;
import com.aedan.jterminal.commands.commandhandler.CommandHandler;
import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelterminal.gelcommandpackage.GelCommandPackage;
import gelframe.GelFrame;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * The Gel terminal.
 */

public class GelTerminal extends JTerminal {

    public GelTerminal(GelFrame gelFrame) {
        super("", new DefaultPackage(), new GelCommandPackage(gelFrame));
    }

}
