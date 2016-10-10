import com.aedan.jterminal.commandpackages.defaultpackage.executors.commands.ExecuteJTermFile;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import gelterminal.Associations;
import gelterminal.GelTerminal;
import gelframe.GelFrame;

import java.io.File;

import gelterminal.gelcommandpackage.commands.LoadDirectory;
import gelterminal.gelcommandpackage.commands.LoadFile;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Main class for Gel editor.
 */

public class Gel {

    /**
     * Main function for Gel editor.
     *
     * @param args The array of arguments for the Gel editor (currently unused).
     */
    public static void main(String[] args) throws Exception {
        Associations.loadAssociations(new File(args[0]));
        GelFrame gelFrame = new GelFrame();
        GelTerminal gelTerminal = new GelTerminal(gelFrame);
        for (String arg : args){
            if (arg.startsWith("startup:")){
                System.out.println("Loading " + arg.substring(8) + ".jterm");
                new ExecuteJTermFile().parse(
                        gelTerminal.getInput(),
                        new CommandArgumentList(new String[]{"exec", arg.substring(8)}),
                        gelTerminal.getEnvironment(),
                        gelTerminal.getOutput()
                );
            }
            if (arg.startsWith("loadf:")){
                System.out.println("Loading file " + arg.substring(6));
                new LoadFile(gelFrame).parse(
                        gelTerminal.getInput(),
                        new CommandArgumentList(new String[]{"loadfile", arg.substring(6)}),
                        gelTerminal.getEnvironment(),
                        gelTerminal.getOutput()
                );
            }
            if (arg.startsWith("loadd:")){
                System.out.println("Loading directory " + arg.substring(6));
                new LoadDirectory(gelFrame).parse(
                        gelTerminal.getInput(),
                        new CommandArgumentList(new String[]{"loaddir", arg.substring(6)}),
                        gelTerminal.getEnvironment(),
                        gelTerminal.getOutput()
                );
            }
        }

        gelTerminal.run();
    }

}
