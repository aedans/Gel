package gelframe.gelfilewindow.geltextpane.gelstylers;

import gelframe.gelfilewindow.geltextpane.GelTextPane;

import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for creating Stylers that color a regex.
 */

public class RegexColorStyler implements GelStyler {

    /**
     * The Pattern for the RegexColorStyler.
     */
    private Pattern pattern;

    /**
     * The AttributeSet for the RegexColorStyler.
     */
    private AttributeSet attributeSet;

    /**
     * Default RegexColorStyler constructor.
     *
     * @param regex The regex for the RegexColorStyler.
     * @param color The Color for the RegexColorStyler.
     */
    public RegexColorStyler(String regex, Color color) {
        this.attributeSet = StyleContext.getDefaultStyleContext().addAttribute(
                StyleContext.getDefaultStyleContext().getEmptySet(), StyleConstants.Foreground, color
        );
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public void style(GelTextPane gelTextPane) {
        Matcher m = pattern.matcher(gelTextPane.getText());
        while (m.find()) {
            gelTextPane.getStyledDocument().setCharacterAttributes(
                    m.start(), m.end()-m.start(), attributeSet, true
            );
        }
    }

}
