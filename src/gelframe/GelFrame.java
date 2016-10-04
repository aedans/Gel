package gelframe;

import javax.swing.*;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating a Gel JFrame.
 */

public class GelFrame extends JFrame {

    public GelFrame(){
        this(new GelTextPane());
    }

    public GelFrame(JTextPane textPane){
        this.add(textPane);

        this.setTitle("Gel");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
        this.requestFocus();
    }

}
