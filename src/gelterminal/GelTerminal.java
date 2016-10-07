package gelterminal;

import com.aedan.jterminal.JTerminal;
import com.aedan.jterminal.commandpackages.defaultpackage.DefaultPackage;
import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import gelterminal.gelcommandpackage.GelCommandPackage;
import gelframe.GelFrame;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Terminal for loading config.jterm files.
 */

public class GelTerminal extends JTerminal {

    public GelTerminal(GelFrame gelFrame){
        this("", gelFrame);
    }

    private GelTerminal(String args, GelFrame gelFrame) {
        this(args, new DefaultPackage(), new GelCommandPackage(gelFrame));
    }

    private GelTerminal(String args, CommandPackage... commandPackages) {
        super(args, commandPackages);
    }

    private GelTerminal(String args, CommandInput input, CommandOutput output, CommandPackage... commandPackages) {
        super(args, input, output, commandPackages);
    }

    /**
     * Executes a .jterm file.
     *
     * @param path The path of the .jterm file.
     * @throws Exception If there was an error loading the .jterm file.
     */
    public void execute(String path) throws Exception {
        this.getEnvironment().handleInput(getInput(), "exec " + path, getOutput());
    }

}
