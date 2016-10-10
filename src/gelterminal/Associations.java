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
    private static LinkedList<File> filePaths = new LinkedList<>();

    /**
     * Loads Associations from a file.
     *
     * @param file The File to load associations from.
     * @throws FileNotFoundException If the associations file cannot be found.
     */
    public static void loadAssociations(File file) throws FileNotFoundException {
        final String[] content = {""};
        new BufferedReader(new FileReader(file)).lines().forEach(s -> content[0] += s + '\n');
        Pattern p = Pattern.compile("([^\n ]+) ([^\n]+)");
        Matcher m = p.matcher(content[0]);
        while (m.find()){
            fileTypes.add(m.group(1));
            filePaths.add(new File(m.group(2)));
        }
    }

    public static File getAssociation(File f) {
        for (int i = 0; i < fileTypes.size(); i++) {
            String s = fileTypes.get(i);
            if (f.getAbsolutePath().endsWith(s)) {
                return filePaths.get(i);
            }
        }
        return filePaths.getLast();
    }

}
