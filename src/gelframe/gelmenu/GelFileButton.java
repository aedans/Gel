package gelframe.gelmenu;

import gelframe.GelFrame;
import gelframe.gelfilewindow.GelFileWindow;
import gelframe.gelfilewindow.geltextpane.GelFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * Created by Aedan Smith on 10/7/2016.
 *
 * Class for creating JButtons to access GelFiles.
 */

class GelFileButton extends JButton {

    public static final int textLength = 24;

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
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        String name = gelFileWindow.getFile().getFile().getName();
        if (name.length() <= textLength){
            for (int i = 0; i < textLength; i++){
                name += ' ';
            }
            name = name.substring(0, textLength);
        } else { 
            name = name.substring(0, textLength-3);
            name += "...";
        }
        
        this.setText(name);
    }

}
