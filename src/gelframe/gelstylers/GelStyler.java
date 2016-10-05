package gelframe.gelstylers;

import gelframe.GelTextPane;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Interface for modifying the GelTextPane Styles.
 */

public interface GelStyler {

    /**
     * Styles a given GelTextPane.
     *
     * @param gelTextPane The GelTextPane to style.
     */
    void style(GelTextPane gelTextPane);

}
