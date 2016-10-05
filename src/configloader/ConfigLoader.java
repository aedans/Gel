package configloader;

import com.aedan.jterminal.JTerminal;
import com.aedan.jterminal.commandpackages.defaultpackage.DefaultPackage;
import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.input.CommandInput;
import com.aedan.jterminal.output.CommandOutput;
import configloader.configcommandpackage.ConfigCommandPackage;
import gelframe.GelFrame;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Terminal for loading config.jterm files.
 */

public class ConfigLoader extends JTerminal {

    public ConfigLoader(GelFrame gelFrame){
        this("", gelFrame);
    }

    private ConfigLoader(String args, GelFrame gelFrame) {
        this(args, new DefaultPackage(), new ConfigCommandPackage(gelFrame));
    }

    private ConfigLoader(String args, CommandPackage... commandPackages) {
        super(args, commandPackages);
    }

    private ConfigLoader(String args, CommandInput input, CommandOutput output, CommandPackage... commandPackages) {
        super(args, input, output, commandPackages);
    }

    /**
     * Loads the GelFrame config.jterm from a given String.
     *
     * @param path The path to load the config.jterm from.
     * @throws Exception If there was an error loading the config.jterm.
     */
    public void loadConfig(String path) throws Exception {
        this.getEnvironment().handleInput(getInput(), "exec " + path, getOutput());
    }

}
