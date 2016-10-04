package gelframe.gelstylers;

import gelframe.GelTextPane;

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

class RegexColorStyler extends GelStyler {

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
    private RegexColorStyler(String regex, Color color) {
        this.attributeSet = StyleContext.getDefaultStyleContext().addAttribute(
                StyleContext.getDefaultStyleContext().getEmptySet(), StyleConstants.Foreground, color
        );
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public void style(GelTextPane gelTextPane) {
        Matcher m = pattern.matcher(gelTextPane.getText().replaceAll("\n", ""));
        while (m.find()) {
            gelTextPane.getStyledDocument().setCharacterAttributes(
                    m.start(), m.end()-m.start(), attributeSet, true
            );
        }
    }

    /**
     * Pattern for the fromString(s) method.
     */
    private static Pattern fromStringPattern = Pattern.compile("(\\d+),(\\d+),(\\d+) (.+)");

    /**
     * Creates a RegexColorStyler from a String.
     *
     * @param s The String to create the RegexColorStyler from.
     * @return The created RegexColorStyler.
     * @throws Exception If there was an error creating the RegexColorStyler.
     */
    static RegexColorStyler fromString(String s) throws Exception{
        Matcher m = fromStringPattern.matcher(s);
        if (m.find()){
            return new RegexColorStyler(
                    m.group(4),
                    new Color(
                            Integer.parseInt(m.group(1)),
                            Integer.parseInt(m.group(2)),
                            Integer.parseInt(m.group(3))
                    )
            );
        } else {
            throw new Exception("Could not create RegexColorStyler from String " + s);
        }
    }

}
