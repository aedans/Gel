import gelterminal.GelTerminal;
import gelframe.GelFrame;

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
        GelFrame gelFrame = new GelFrame();
        GelTerminal gelTerminal = new GelTerminal(gelFrame);
        for (String arg : args){
            if (arg.startsWith("startup:")){
                System.out.println("Loading " + arg.substring(8) + ".jterm");
                gelTerminal.execute(arg.substring(8));
            }
            if (arg.startsWith("load:")){
                System.out.println("Loading file " + arg.substring(5));
                gelFrame.loadFile(arg.substring(5));
            }
        }

        gelTerminal.run();
    }
    
}
