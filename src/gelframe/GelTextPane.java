package gelframe;

import gelframe.gellisteners.GelListener;
import gelframe.gelstylers.GelStyler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating Gel TextPanes.
 */

public class GelTextPane extends JTextPane {

    /**
     * A List of things to do on the next render.
     */
    public LinkedList<Runnable> toDo = new LinkedList<>();

    /**
     * If the GelTextPane has changed since last render.
     */
    private boolean hasChanged = true;

    /**
     * The StyleContext for the GelTextPane.
     */
    private StyleContext styleContext = StyleContext.getDefaultStyleContext();

    /**
     * The default AttributeSet for the GelTextPane.
     */
    public final AttributeSet def = styleContext.addAttribute(
            styleContext.getEmptySet(), StyleConstants.Foreground, Color.WHITE
    );

    /**
     * The List of GelStylers for the GelTextPane
     */
    private LinkedList<GelStyler> stylers = new LinkedList<>();

    /**
     * Default GelTextPane constructor.
     */
    GelTextPane(File file){
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        this.setBackground(new Color(0, 0, 0));
        this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        this.setCaretColor(Color.WHITE);

        new Thread(new AutoSaver(file, getStyledDocument(), 250)).start();

        this.addKeyListener(new GelListener(this));

        this.getStyledDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                hasChanged = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                hasChanged = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                hasChanged = true;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        toDo.forEach(Runnable::run);
        toDo = new LinkedList<>();

        if (hasChanged) {
            // Recolors text
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
            } catch (ConcurrentModificationException ignored) {}

            hasChanged = false;
        }

        super.paint(g);
    }

    /**
     * Adds a GelStyler to the GelTextPane.
     *
     * @param gelStyler The GelStyler to add.
     */
    public void addStyler(GelStyler gelStyler){
        stylers.add(gelStyler);
        gelStyler.style(this);
    }

    @Override
    public void setText(String string){
        super.setText(string);
        hasChanged = true;
    }

}
