package gelframe.gelmenu;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aedan Smith on 10/6/2016.
 *
 * GelMenu class.
 */

public class GelMenu extends JMenuBar {

    @Override
    public Component add(Component component){
        try {
            return super.add(component);
        } finally {
            super.updateUI();
        }
    }

}
