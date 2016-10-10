package gelframe.gelmenu;

import gelframe.GelFrame;
import gelframe.gelfilewindow.GelFileWindow;
import gelframe.gelfilewindow.geltextpane.GelFile;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Class for creating JButtons to access GelFiles.
 */

class GelFileButton extends JButton {

    /**
     * Default GelFileButton constructor.
     *
     * @param gelFileWindow The File for the Button to switch to.
     * @param gelFrame The GelFrame containing the GelFileButton.
     */
    GelFileButton(GelFileWindow gelFileWindow, GelFrame gelFrame){
        this.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gelFrame.setVisibleWindow(gelFileWindow);
                } catch (Exception n){
                    n.printStackTrace();
                }
            }
        });
        this.setText(gelFileWindow.getFile().getFile().getName());
    }

}
