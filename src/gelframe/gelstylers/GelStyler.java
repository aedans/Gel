package gelframe.gelstylers;

import gelframe.GelTextPane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for modifying the GelTextPane Styles.
 */

public abstract class GelStyler {

    /**
     * Styles a given GelTextPane.
     *
     * @param gelTextPane The GelTextPane to style.
     */
    public abstract void style(GelTextPane gelTextPane);

    /**
     * Creates a GelStyles from a File.
     *
     * @param file The File to create the GelStyler from.
     * @return The created GelStyler.
     * @throws Exception If there was an error creating the GelStyler
     */
    public static GelStyler fromFile(File file) throws Exception {
        final String[] content = {""};
        new BufferedReader(new FileReader(file)).lines().forEach(s -> content[0] += s + '\n');
        return fromString(content[0]);
    }

    /**
     * Creates a GelStyle from a String.
     *
     * @param string The String to create the GelStyler from.
     * @return The created GelStyler.
     * @throws Exception If there was an error creating the GelStyler
     */
    private static GelStyler fromString(String string) throws Exception {
        if (string.startsWith("COLOR")){
            return RegexColorStyler.fromString(string.substring(6));
        }
        throw new Exception("Could not parse String: " + string);
    }

}
