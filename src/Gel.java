import com.aedan.jterminal.commandpackages.defaultpackage.executors.commands.ExecuteJTermFile;
import com.aedan.jterminal.commands.commandarguments.CommandArgumentList;
import gelterminal.Associations;
import gelterminal.GelTerminal;
import gelframe.GelFrame;

import java.io.File;
import java.util.ArrayList;

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
                ArrayList<String> exec = new ArrayList<>();
                exec.add("exec");
                exec.add(arg.substring(8));
                new ExecuteJTermFile().parse(
                        gelTerminal.getInput(),
                        new CommandArgumentList(exec),
                        gelTerminal.getEnvironment(),
                        gelTerminal.getOutput()
                );
            }
            if (arg.startsWith("loadf:")){
                System.out.println("Loading file " + arg.substring(6));
                ArrayList<String> exec = new ArrayList<>();
                exec.add("loadfile");
                exec.add(arg.substring(6));
                new LoadFile(gelFrame).parse(
                        gelTerminal.getInput(),
                        new CommandArgumentList(exec),
                        gelTerminal.getEnvironment(),
                        gelTerminal.getOutput()
                );
            }
            if (arg.startsWith("loadd:")){
                System.out.println("Loading directory " + arg.substring(6));
                ArrayList<String> exec = new ArrayList<>();
                exec.add("loaddir");
                exec.add(arg.substring(6));
                new LoadDirectory(gelFrame).parse(
                        gelTerminal.getInput(),
                        new CommandArgumentList(exec),
                        gelTerminal.getEnvironment(),
                        gelTerminal.getOutput()
                );
            }
        }

        gelTerminal.run();
    }

}
