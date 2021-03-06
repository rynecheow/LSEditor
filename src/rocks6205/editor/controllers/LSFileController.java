package rocks6205.editor.controllers;

//~--- JDK imports ------------------------------------------------------------

import java.io.File;
import java.io.IOException;

/**
 * Controller that deals with handling of files, which are the basic file operations :
 * Save and load file, and also tracks the modification.
 *
 * @author Cheow Yeong Chi
 * @version 2.1
 */
public interface LSFileController {
    public static final File NEW_DOCUMENT = new File("file:///untitled.svg");

    /**
     * Loads file from two ways:<p>
     * 1) Read directly from <code>JFileChooser</code>.<br>
     * 2) File from path string passed by command line argument.<p>
     * <p>
     * File is then parsed into a <code>Document</code> object and rendered.
     *
     * @param file File
     * @return If file is successfully loaded
     */
    public boolean fileLoad(File file) throws IOException;

    /**
     * Saves current file
     *
     * @return True if file is successfully saved, false otherwise.
     * @throws IOException
     */
    public boolean fileSave() throws IOException;

    /**
     * @param file  File
     * @return boolean
     * @throws IOException
     */
    public boolean fileSave(File file) throws IOException;

    /**
     *
     */
    public void fileClose();

    public void endFileModification();
}