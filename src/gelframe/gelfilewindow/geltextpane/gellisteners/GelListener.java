package gelframe.gelfilewindow.geltextpane.gellisteners;

import gelframe.gelfilewindow.geltextpane.GelTextPane;

import javax.swing.text.BadLocationException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aedan Smith on 10/5/2016.
 *
 * KeyListener for modifying text after newlines.
 */

public class GelListener implements KeyListener {

    /**
     * The GelTextPane for the GelListener to listen to.
     */
    private GelTextPane gelTextPane;

    /**
     * Default GelListener constructor.
     *
     * @param gelTextPane The GelTextPane for the GelListener to listen to.
     */
    public GelListener(GelTextPane gelTextPane){
        this.gelTextPane = gelTextPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.isShiftDown() && e.getKeyChar() == '\n'){
            try {
                gelTextPane.getStyledDocument().insertString(gelTextPane.getCaretPosition(), "\n", gelTextPane.def);
                return;
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getKeyChar() == '\n'){
            try {
                Matcher m = Pattern.compile("( +)*[^\n]*").matcher(gelTextPane.getText());
                if (m.find(beginLine(gelTextPane.getText(), gelTextPane.getCaretPosition()-2))){
                    gelTextPane.getStyledDocument().insertString(gelTextPane.getCaretPosition(), m.group(1), gelTextPane.def);
                }
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getKeyChar() == '\t'){
            gelTextPane.toDo.add(() -> {
                try {
                    gelTextPane.getStyledDocument().remove(gelTextPane.getCaretPosition()-1, 1);
                    gelTextPane.getStyledDocument().insertString(gelTextPane.getCaretPosition(), "    ", gelTextPane.def);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            });
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private int beginLine(String s, int position){
        for (; position > 0; position--){
            if (s.charAt(position) == '\n'){
                return position+1;
            }
        }
        return 0;
    }

}
