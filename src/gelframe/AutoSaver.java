package gelframe;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Aedan Smith on 10/4/2016.
 *
 * Automatically saves.
 */

public class AutoSaver implements Runnable {

    /**
     * The File for the AutoSaver to save to.
     */
    private File file;

    /**
     * The StyledDocument for the AutoSaver to save from.
     */
    private StyledDocument styledDocument;

    /**
     * The number of milliseconds between saves.
     */
    private int i;

    /**
     * Default AutoSaver constructor.
     *  @param file The File for the AutoSaver to save to.
     * @param styledDocument The StyledDocument for the AutoSaver to save from.
     * @param i The number of milliseconds between saves.
     */
    AutoSaver(File file, StyledDocument styledDocument, int i){
        this.file = file;
        this.styledDocument = styledDocument;
        this.i = i;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                for (char c : styledDocument.getText(0, styledDocument.getLength()).toCharArray()) {
                    assert fileOutputStream != null;
                    fileOutputStream.write(c);
                }
            } catch (BadLocationException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
