import configloader.ConfigLoader;
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
        GelFrame gelFrame = new GelFrame((args.length > 1) ? args[1] : "temp.txt");
        ConfigLoader configLoader = new ConfigLoader(gelFrame);
        configLoader.loadConfig(args[0]);
        configLoader.run();
    }

}
