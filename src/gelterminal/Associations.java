package gelterminal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Class containing filetype associations.
 */

public final class Associations {

    /**
     * The List of associated File types.
     */
    private static LinkedList<String> fileTypes = new LinkedList<>();

    /**
     * The List of associated File paths.
     */
    private static LinkedList<String> associations = new LinkedList<>();

    /**
     * Loads Associations from a file.
     *
     * @param file The File to load associations from.
     * @throws FileNotFoundException If the associations file cannot be found.
     */
    public static void loadAssociations(File file) throws FileNotFoundException {
        for (File f : file.listFiles()){
            final String[] content = {""};
            new BufferedReader(new FileReader(f)).lines().forEach(s -> content[0] += s + '\n');
            fileTypes.add(f.getName());
            associations.add(content[0]);
        }
    }

    public static String getAssociation(File f) {
        for (int i = 0; i < fileTypes.size(); i++) {
            String s = fileTypes.get(i);
            if (f.getAbsolutePath().endsWith(s)) {
                return associations.get(i).trim();
            }
        }
        return associations.getLast().trim();
    }

}
