package gelframe.gelfilewindow;

import gelframe.gelmenu.GelMenu;
import gelframe.gelfilewindow.geltextpane.GelFile;
import gelframe.gelfilewindow.geltextpane.GelTextPane;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Class for viewing and editing GelFiles.
 */

public class GelFileWindow extends JComponent {

    /**
     * The TextPane for the GelFileWindow.
     */
    private GelTextPane gelTextPane;

    /**
     * The GelMenu for the GelFileWindow.
     */
    private GelMenu gelMenu;

    /**
     * The File that the GelFileWindow is modifying.
     */
    private GelFile file;

    /**
     * Default GelFileWindow constructor.
     *
     * @param file The File for the GelFileWindow to view.
     */
    public GelFileWindow(GelFile file){
        this.file = file;

        this.setLayout(new BorderLayout());

        this.gelTextPane = new GelTextPane(file);
        this.add(gelTextPane, BorderLayout.CENTER);

        JScrollPane jScrollPane = new JScrollPane(gelTextPane);
        this.add(jScrollPane, BorderLayout.CENTER);

        this.add(gelMenu = new GelMenu(), BorderLayout.NORTH);
    }

    /**
     * Saves the GelFile.
     *
     * @throws IOException If the GelFile cannot be saved.
     */
    public void save() throws IOException {
        file.setContent(gelTextPane.getText());
        file.save();
    }

    public GelFile getFile() {
        return file;
    }

    public GelMenu getGelMenu() {
        return gelMenu;
    }

    public GelTextPane getGelTextPane() {
        return gelTextPane;
    }

}
