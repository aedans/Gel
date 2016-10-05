package configloader.configcommandpackage;

import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.environment.Environment;
import configloader.configcommandpackage.commands.AddRegexColorer;
import configloader.configcommandpackage.commands.AddStyler;
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
        environment.addCommand(new AddRegexColorer(gelFrame));
        environment.addCommand(new AddStyler());
    }

}
