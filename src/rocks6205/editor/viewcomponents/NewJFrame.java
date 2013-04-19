/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks6205.editor.mvc;

/**
 *
 * @author
 * Cheow
 * Yeong
 * Chi
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates
     * new
     * form
     * NewJFrame
     */
    public NewJFrame() {
        initComponents();
    }

    /**
     * This
     * method
     * is
     * called
     * from
     * within
     * the
     * constructor
     * to
     * initialize
     * the
     * form.
     * WARNING:
     * Do
     * NOT
     * modify
     * this
     * code.
     * The
     * content
     * of
     * this
     * method
     * is
     * always
     * regenerated
     * by
     * the
     * Form
     * Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topBar = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        sideBar = new javax.swing.JToolBar();
        miscPanel = new javax.swing.JPanel();
        navigationPanel = new javax.swing.JPanel();
        statusBar = new javax.swing.JPanel();
        editPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1183, 818));
        setMinimumSize(new java.awt.Dimension(1183, 818));

        topBar.setBackground(new java.awt.Color(51, 51, 51));
        topBar.setFloatable(false);
        topBar.setRollover(true);
        topBar.setName(""); // NOI18N

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton2.setSize(new java.awt.Dimension(30, 30));
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        topBar.add(jButton2);

        sideBar.setBackground(new java.awt.Color(51, 51, 51));
        sideBar.setFloatable(false);
        sideBar.setOrientation(javax.swing.SwingConstants.VERTICAL);
        sideBar.setRollover(true);

        miscPanel.setBackground(new java.awt.Color(51, 51, 51));

        org.jdesktop.layout.GroupLayout miscPanelLayout = new org.jdesktop.layout.GroupLayout(miscPanel);
        miscPanel.setLayout(miscPanelLayout);
        miscPanelLayout.setHorizontalGroup(
            miscPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        miscPanelLayout.setVerticalGroup(
            miscPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 95, Short.MAX_VALUE)
        );

        navigationPanel.setBackground(new java.awt.Color(51, 51, 51));

        org.jdesktop.layout.GroupLayout navigationPanelLayout = new org.jdesktop.layout.GroupLayout(navigationPanel);
        navigationPanel.setLayout(navigationPanelLayout);
        navigationPanelLayout.setHorizontalGroup(
            navigationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 246, Short.MAX_VALUE)
        );
        navigationPanelLayout.setVerticalGroup(
            navigationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        statusBar.setBackground(new java.awt.Color(51, 51, 51));

        org.jdesktop.layout.GroupLayout statusBarLayout = new org.jdesktop.layout.GroupLayout(statusBar);
        statusBar.setLayout(statusBarLayout);
        statusBarLayout.setHorizontalGroup(
            statusBarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        statusBarLayout.setVerticalGroup(
            statusBarLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 22, Short.MAX_VALUE)
        );

        editPanel.setBackground(new java.awt.Color(51, 51, 51));

        org.jdesktop.layout.GroupLayout editPanelLayout = new org.jdesktop.layout.GroupLayout(editPanel);
        editPanel.setLayout(editPanelLayout);
        editPanelLayout.setHorizontalGroup(
            editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 890, Short.MAX_VALUE)
        );
        editPanelLayout.setVerticalGroup(
            editPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 626, Short.MAX_VALUE)
        );

        jMenu1.setText("File");
        menuBar.add(jMenu1);

        jMenu2.setText("Edit");
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(sideBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(editPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(statusBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(miscPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(navigationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(topBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(topBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(sideBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(statusBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(editPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(miscPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(navigationPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 1183, 840);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param
     * args
     * the
     * command
     * line
     * arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel editPanel;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPanel miscPanel;
    private javax.swing.JPanel navigationPanel;
    private javax.swing.JToolBar sideBar;
    private javax.swing.JPanel statusBar;
    private javax.swing.JToolBar topBar;
    // End of variables declaration//GEN-END:variables
}
