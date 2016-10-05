package gelframe;

import gelframe.gelstylers.GelStyler;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Objects;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating a Gel JFrame.
 */

public class GelFrame extends JFrame {

    /**
     * The TextPane for the GelFrame.
     */
    private GelTextPane gelTextPane;

    private GelFrame(File file){
        this.setLayout(new BorderLayout());

        this.gelTextPane = new GelTextPane(file);
        this.add(gelTextPane, SwingConstants.CENTER);

        JScrollPane jScrollPane = new JScrollPane(gelTextPane);
        this.add(jScrollPane, BorderLayout.CENTER);

        this.setTitle("Gel");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.requestFocus();
    }

    public GelFrame(String arg) throws IOException {
        this(new File(arg));
        final String[] content = {""};
        new BufferedReader(new FileReader(new File(arg))).lines().forEach(s -> content[0] += s + '\n');
        this.gelTextPane.setText(content[0]);
    }

    /**
     * Loads the GelFrame config from a given File.
     *
     * @param file The File to load the config from.
     * @throws Exception If there was an error loading the config.
     */
    public void loadConfig(File file) throws Exception {
        final String[] content = {""};
        new BufferedReader(new FileReader(file)).lines().forEach(s -> content[0] += s + '\n');
        loadConfig(content[0]);
    }

    /**
     * Loads the GelFrame config from a given String.
     *
     * @param config The String to load the config from.
     * @throws Exception If there was an error loading the config.
     */
    private void loadConfig(String config) throws Exception {
        for (String s : config.split("\n")){
            if (Objects.equals(s.substring(0, 6), "styler")){
                gelTextPane.addStyler(GelStyler.fromFile(new File(s.substring(7))));
            }
        }
    }

}
