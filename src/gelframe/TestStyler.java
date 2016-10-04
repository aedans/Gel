package gelframe;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Test
 */

class TestStyler extends GelStyler {

    TestStyler(StyleContext styleContext) {
        super(styleContext.addAttribute(
                styleContext.getEmptySet(), StyleConstants.Foreground, Color.GREEN
        ));
    }

    @Override
    public void style(GelTextPane gelTextPane) {
        Matcher m = Pattern.compile("//.*").matcher(gelTextPane.getText().replaceAll("\n", ""));
        while (m.find()) {
            gelTextPane.getStyledDocument().setCharacterAttributes(
                    m.start(), m.end()-m.start(), attributeSet, true
            );
        }
    }

}
