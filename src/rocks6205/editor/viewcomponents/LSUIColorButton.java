package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.adt.LSPainting;
import rocks6205.editor.model.adt.LSPaintingType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//~--- JDK imports ------------------------------------------------------------

/**
 * Custom JButtons that use to control colors of elements using a pop up.
 *
 * @author Cheow Yeong Chi
 * @since 2.4
 */
public final class LSUIColorButton extends JButton implements LSUIProtocol {
    protected LSPainting painting;
    private JColorChooser colorChooser;
    private LSUIButton noneButton;
    private JPopupMenu popOver;
    Point point;
    private JPanel chooserPanel;

    private LSUIColorButton(LSPainting p) {
        painting = p;
        initialise();
        customise();
    }

    /**
     * Factory method.
     *
     * @param painting Paint object
     * @return new instance of <code>LSUIColorButton</code>
     */
    public static LSUIColorButton create(LSPainting painting) {
        return new LSUIColorButton(painting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise() {
        colorChooser = new JColorChooser(painting.getPaintColor());
        popOver = new JPopupMenu();
        chooserPanel = new JPanel();
        noneButton = LSUIButton.create();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void customise() {
        layoutComponents();
        layoutParentComponent();
        bindHandlers();
    }

    private void layoutComponents() {
        chooserPanel.add(colorChooser);
        chooserPanel.add(noneButton);
        popOver.insert(chooserPanel, 0);
        colorChooser.setPreviewPanel(new JPanel());
    }

    private void layoutParentComponent() {
        setFocusable(false);

        Dimension d = new Dimension(25, 25);

        setMinimumSize(d);
        setMaximumSize(d);
        setPreferredSize(d);
        setSize(d);
        setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;

                if (painting.getPaintType() == LSPaintingType.NONE) {
                    g2d.setPaint(Color.WHITE);
                    g2d.fillRect(x, y, 24, 24);
                    g2d.setPaint(Color.RED);
                    g2d.setStroke(new BasicStroke(2.5f));
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.drawLine(x, 23 + x, 23 + y, y);
                } else if (painting.getPaintType() == LSPaintingType.COLOR) {
                    g2d.setPaint((Color) painting.getPaintColor());
                    g2d.fillRect(1, 1, 23, 23);
                }
            }

            @Override
            public int getIconWidth() {
                return 25;
            }

            @Override
            public int getIconHeight() {
                return 25;
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }

                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindHandlers() {
        colorChooser.getSelectionModel().addChangeListener(e -> {
            setPainting(new LSPainting(colorChooser.getColor()));
            fireStateChanged();
        });
        noneButton.addActionListener(e -> setPaintingNone());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point = getTopLevelAncestor().getMousePosition(true);
            }
        });
        addActionListener(e -> popOver.show(getTopLevelAncestor(), point.x, point.y));
    }

    /**
     * Add change listener for <i>presentation</i> to current list of listeners.
     *
     * @param l Change listener
     */
    public void addPresentationChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    /**
     * Remove change listener for <i>presentation</i> to current list of listeners.
     *
     * @param l
     */
    public void removePresentationChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    /*
     * ACCESSOR
     */

    /**
     * @return Current paint object
     */
    public LSPainting getPainting() {
        return painting;
    }

    /*
     * MUTATORS.
     */

    /**
     * Set paint for current button and fires changes.
     *
     * @param p Paint object
     */
    public void setPainting(LSPainting p) {
        setPaint(p);
        fireStateChanged();
    }

    private void setPaint(LSPainting p) {
        painting = p;
    }

    /**
     * Set current button's <code>paint</code> component to <i>NONE</i>.
     */
    public void setPaintingNone() {
        setPainting(new LSPainting(LSPaintingType.NONE));
    }
}