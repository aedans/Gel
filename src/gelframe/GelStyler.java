package gelframe;

import javax.swing.text.AttributeSet;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Class for modifying the GelTextPane Styles.
 */

public abstract class GelStyler {

    protected AttributeSet attributeSet;

    public GelStyler(AttributeSet attributeSet){
        this.attributeSet = attributeSet;
    }

    public abstract void style(GelTextPane gelTextPane);

}
