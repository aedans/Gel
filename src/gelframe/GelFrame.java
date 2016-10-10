package gelframe;

import gelframe.gelfilewindow.GelFileWindow;
import gelframe.gelmenu.GelFileMenu;
import gelframe.gelfilewindow.geltextpane.GelFile;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating a Gel JFrame.
 */

public class GelFrame extends JFrame {

    /**
     * The GelFileMenu for the GelFrame.
     */
    private GelFileMenu gelFileMenu = new GelFileMenu();

    /**
     * The List of GelFileWindows in the GelFrame.
     */
    private ArrayList<GelFileWindow> gelFileWindows = new ArrayList<>();

    /**
     * The active GelFileWindow.
     */
    private GelFileWindow activeWindow;

    /**
     * Default GelFrame constructor.
     */
    public GelFrame(){
        this.setLayout(new BorderLayout());

        this.add(gelFileMenu, BorderLayout.LINE_START);

        this.setTitle("Gel");
        this.setSize(1080, 720);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.requestFocus();
    }

    /**
     * Loads a GelFile into the GelFrame with a GelFileWindow.
     *
     * @param file The File to load.
     * @throws FileNotFoundException If the File cannot be found.
     */
    public void loadFile(GelFile file) throws FileNotFoundException {
        GelFileWindow gelFileWindow = new GelFileWindow(file);

        final String[] content = {""};
        new BufferedReader(new FileReader(file.getFile())).lines().forEach(s -> content[0] += s + '\n');
        gelFileWindow.getGelTextPane().setText(content[0]);

        this.gelFileMenu.add(gelFileWindow, this);
        this.add(gelFileWindow, BorderLayout.CENTER);
        this.setVisibleWindow(gelFileWindow);

        this.gelFileWindows.add(gelFileWindow);

        this.getRootPane().updateUI();
        this.getLayeredPane().updateUI();
    }

    /**
     * Sets the visible GelFileWindow.
     *
     * @param gelFileWindow The GelFileWindow to set visible.
     */
    public void setVisibleWindow(GelFileWindow gelFileWindow){
        for (GelFileWindow gelFileWindow1 : gelFileWindows){
            gelFileWindow1.setVisible(false);
        }
        gelFileWindow.setVisible(true);
        this.activeWindow = gelFileWindow;
    }

    /**
     * Saves all currently open GelFiles.
     */
    public void saveAll() {
        for (GelFileWindow gelFileWindow : gelFileWindows){
            try {
                gelFileWindow.save();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public GelFileWindow getActiveWindow() {
        return activeWindow;
    }

    public GelFileMenu getGelFileMenu() {
        return gelFileMenu;
    }

    public ArrayList<GelFileWindow> getGelFileWindows() {
        return gelFileWindows;
    }

}
