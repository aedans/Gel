package gelframe;

import gelframe.gelstylers.GelStyler;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating Gel TextPanes.
 */

public class GelTextPane extends JTextPane {

    /**
     * The StyleContext for the GelTextPane.
     */
    private StyleContext styleContext = StyleContext.getDefaultStyleContext();

    /**
     * The default AttributeSet for the GelTextPane.
     */
    private final AttributeSet def = styleContext.addAttribute(
            styleContext.getEmptySet(), StyleConstants.Foreground, Color.WHITE
    );

    /**
     * The List of GelStylers for the GelTextPane
     */
    private LinkedList<GelStyler> stylers = new LinkedList<>();

    /**
     * Default GelTextPane constructor.
     */
    GelTextPane(){
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        this.setBackground(new Color(0, 0, 0));
        this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.setCaretColor(Color.WHITE);
    }

    @Override
    public void paint(Graphics g){
        this.getStyledDocument().setCharacterAttributes(
                0,
                getStyledDocument().getLength(),
                def,
                true
        );

        try {
            for (GelStyler styler : stylers) {
                styler.style(this);
            }
        } catch (ConcurrentModificationException ignored){}

        super.paint(g);
    }

    /**
     * Adds a GelStyler to the GelTextPane.
     *
     * @param gelStyler The GelStyler to add.
     */
    void addStyler(GelStyler gelStyler){
        stylers.add(gelStyler);
    }

}
