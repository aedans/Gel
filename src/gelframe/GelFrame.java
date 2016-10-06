package gelframe;

import gelframe.gelmenu.GelMenu;

import javax.swing.*;
import java.awt.*;
import java.io.*;

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

    /**
     * The GelMenu for the GelFrame.
     */
    private GelMenu gelMenu;

    private GelFrame(File file){
        this.setLayout(new BorderLayout());

        this.gelTextPane = new GelTextPane(file);
        this.add(gelTextPane, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane(gelTextPane);
        this.add(jScrollPane, BorderLayout.CENTER);

        this.add(gelMenu = new GelMenu(), BorderLayout.NORTH);

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

    public GelTextPane getGelTextPane() {
        return gelTextPane;
    }

    public GelMenu getGelMenu() {
        return gelMenu;
    }

}
