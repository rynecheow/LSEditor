package rocks6205.editor.controllers;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.IOException;

public interface SVGEditorFileController {

    /**
     * Loads file from two ways:<p>
     * 1) Read directly from <code>JFileChooser</code>.<br>
     * 2) File from path string passed by command line argument.<p>
     *
     * File is then parsed into a <code>Document</code> object and rendered.
     *
     * @param file
     * @return If file is successfully loaded
     */
    public boolean fileLoad(File file) throws IOException;

    /**
     * Saves current file
     * @return True if file is successfully saved, false otherwise.
     * @throws IOException
     */
    public boolean fileSave() throws IOException;

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public boolean fileSave(File file) throws IOException;

    /**
     *
     */
    public void fileClose();

    public void endFileModification();
}