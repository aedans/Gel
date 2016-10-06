package gelframe.gellisteners;

import gelframe.GelTextPane;

import javax.swing.*;
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

public class NewlineListener implements KeyListener {

    private GelTextPane gelTextPane;

    public NewlineListener(GelTextPane gelTextPane){
        this.gelTextPane = gelTextPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
