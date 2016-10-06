package configloader.configcommandpackage;

import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.environment.Environment;
import com.aedan.jterminal.environment.variables.Variable;
import configloader.configcommandpackage.commands.AddButton;
import configloader.configcommandpackage.commands.AddRegexColorer;
import configloader.configcommandpackage.commands.AddStyler;
import configloader.configcommandpackage.commands.ReplaceAll;
import gelframe.GelFrame;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Command package for the config.jterm commands.
 */

public class ConfigCommandPackage implements CommandPackage {

    private GelFrame gelFrame;

    public ConfigCommandPackage(GelFrame gelFrame) {
        this.gelFrame = gelFrame;
    }

    @Override
    public void addCommands(Environment environment) {
        environment.addCommand(new AddButton(gelFrame.getGelMenu()));
        environment.addCommand(new AddRegexColorer(gelFrame.getGelTextPane()));
        environment.addCommand(new AddStyler());
        environment.addCommand(new ReplaceAll(gelFrame.getGelTextPane()));

        environment.addEnvironmentVariable(new Variable() {
            @Override
            public String getName() {
                return "TEXT";
            }

            @Override
            public String getValue() {
                return gelFrame.getGelTextPane().getText();
            }
        });
    }

}
