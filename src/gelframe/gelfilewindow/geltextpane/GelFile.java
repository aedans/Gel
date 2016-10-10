package gelframe.gelfilewindow.geltextpane;

import java.io.*;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Class for loading Files into Gel.
 */

public class GelFile {

    /**
     * The content of the GelFile.
     */
    private String content;

    /**
     * The File for the GelFile to read and write to.
     */
    private File file;

    /**
     * Default GelFile constructor.
     *
     * @param file The File for the GelFile to read and write to.
     * @throws FileNotFoundException If the File cannot be found.
     */
    public GelFile(File file) throws FileNotFoundException {
        this.file = file;
        final String[] content = {""};
        new BufferedReader(new FileReader(file)).lines().forEach(s -> content[0] += s + '\n');
        this.content = content[0];
    }

    /**
     * Saves the GelFile.
     *
     * @throws IOException If there is an error saving the GelFile.
     */
    public void save() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
