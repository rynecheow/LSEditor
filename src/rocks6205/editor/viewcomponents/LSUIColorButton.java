package rocks6205.editor.viewcomponents;

//~--- non-JDK imports --------------------------------------------------------

import rocks6205.editor.model.adt.LSPainting;
import rocks6205.editor.model.adt.LSPaintingType;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Cheow Yeong Chi
 */
public final class LSUIColorButton extends JButton implements LSUIProtocol {
    protected LSPainting  painting;
    private JColorChooser colorChooser;
    private LSUIButton    noneButton;
    private JPopupMenu    popOver;
    Point                 point;
    private JPanel        chooserPanel;

    private LSUIColorButton(LSPainting p) {
        painting = p;
        initialise();
        customise();
    }

    public static LSUIColorButton create(LSPainting painting) {
        return new LSUIColorButton(painting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialise() {
        colorChooser = new JColorChooser(painting.getPaintColor());
        popOver      = new JPopupMenu();
        chooserPanel = new JPanel();
        noneButton   = LSUIButton.create();
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
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setPainting(new LSPainting(colorChooser.getColor()));
                fireStateChanged();
            }
        });
        noneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPaintingNone();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point = getTopLevelAncestor().getMousePosition(true);
            }
        });
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popOver.show(getTopLevelAncestor(), point.x, point.y);
            }
        });
    }

    public void addPresentationChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    public void removePresentationChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    public LSPainting getPainting() {
        return painting;
    }

    public void setPainting(LSPainting p) {
        setPaint(p);
        fireStateChanged();
    }

    public void setPaint(LSPainting p) {
        painting = p;
    }

    public void setPaintingNone() {
        setPainting(new LSPainting(LSPaintingType.NONE));
    }
}