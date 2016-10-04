package gelframe;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating Gel TextPanes.
 */

class GelTextPane extends JTextPane {

    private StyleContext styleContext = StyleContext.getDefaultStyleContext();

    private final AttributeSet def = styleContext.addAttribute(
            styleContext.getEmptySet(), StyleConstants.Foreground, Color.WHITE
    );

    private TestStyler testStyler = new TestStyler(styleContext);


    public GelTextPane(){
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        this.setBackground(new Color(0, 0, 0));
        this.setCursor(new Cursor(Cursor.TEXT_CURSOR));
    }

    @Override
    public void paint(Graphics g){
        GelTextPane.this.setCharacterAttributes(def, true);

        testStyler.style(this);

        super.paint(g);
    }

}
