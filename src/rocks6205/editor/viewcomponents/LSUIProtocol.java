package rocks6205.editor.viewcomponents;

/**
 * Protocol that UI components must implement for proper coding style.
 *
 * @author Toh Huey Jing
 * @since 2.2
 */
public interface LSUIProtocol {

    /**
     * Initialisation of GUI components.
     */
    void initialise();

    /**
     * Customisation of GUI components.
     */
    void customise();

    /**
     * Bind action handlers to components.
     */
    void bindHandlers();
}