package gelframe.gelmenu;

import gelframe.GelFrame;
import gelframe.gelfilewindow.GelFileWindow;
import gelframe.gelfilewindow.geltextpane.GelFile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Class containing the FileList for the GelFrame.
 */

public class GelFileMenu extends JMenuBar {

    /**
     * Default GelFileMenu constructor.
     */
    public GelFileMenu(){
        this.setLayout(new BoxLayout(this, 1));
    }

    /**
     * Adds a GelFileWindow to the GelFileMenu.
     *
     * @param gelFileWindow The GelFileWindow to add.
     * @param gelFrame The GelFrame containing the GelFileMenu
     */
    public void add(GelFileWindow gelFileWindow, GelFrame gelFrame) {
        this.add(new GelFileButton(gelFileWindow, gelFrame));
        this.updateUI();
    }

}
