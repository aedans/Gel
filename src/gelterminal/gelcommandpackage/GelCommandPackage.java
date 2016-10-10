package gelterminal.gelcommandpackage;

import com.aedan.jterminal.commands.CommandPackage;
import com.aedan.jterminal.environment.Environment;
import gelterminal.Associations;
import gelterminal.gelcommandpackage.commands.*;
import gelframe.GelFrame;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * Command package for the config.jterm commands.
 */

public class GelCommandPackage implements CommandPackage {

    private GelFrame gelFrame;

    public GelCommandPackage(GelFrame gelFrame) {
        this.gelFrame = gelFrame;
    }

    @Override
    public void addCommands(Environment environment) {
        environment.addCommand(new AddButton(gelFrame));
        environment.addCommand(new AddRegexColorer(gelFrame));
        environment.addCommand(new Refresh(gelFrame));
        environment.addCommand(new LoadFile(gelFrame));
//        environment.addCommand(new LoadDirectory(gelFrame));
        environment.addCommand(new SaveAll(gelFrame));

//        environment.addEnvironmentVariable(new Variable() {
//            @Override
//            public String getName() {
//                return "TEXT";
//            }
//
//            @Override
//            public String getValue() {
//                return gelFrame.getActiveWindow().getGelTextPane().getText();
//            }
//        });
    }

}
