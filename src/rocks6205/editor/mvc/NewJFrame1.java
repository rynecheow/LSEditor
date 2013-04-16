/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks6205.editor.mvc;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author
 * Cheow
 * Yeong
 * Chi
 */
public class NewJFrame1 extends javax.swing.JFrame {

    /**
     * Creates
     * new
     * form
     * NewJFrame1
     */
    public NewJFrame1() {
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

        JToolBar topBar = new JToolBar();
        JToolBar sideBar = new JToolBar();
        JPanel statusPanel = new JPanel();
        JPanel navPanel = new JPanel();
        JPanel miscPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        JPanel jPanel1 = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu jMenu1 = new JMenu();
        JMenu jMenu2 = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new Dimension(1183, 818));
        setMinimumSize(new Dimension(1183, 818));
        getContentPane().setLayout(null);

        topBar.setBackground(new Color(0, 51, 51));
        topBar.setFloatable(false);
        topBar.setRollover(true);
        topBar.setName("topBar"); // NOI18N
        getContentPane().add(topBar);
        topBar.setBounds(0, 0, 1183, 35);

        sideBar.setBackground(new Color(0, 51, 51));
        sideBar.setFloatable(false);
        sideBar.setOrientation(SwingConstants.VERTICAL);
        sideBar.setRollover(true);
        sideBar.setName("sideBar"); // NOI18N
        getContentPane().add(sideBar);
        sideBar.setBounds(0, 36, 35, 760);

        statusPanel.setBackground(new Color(0, 51, 51));
        statusPanel.setName("statusPanel"); // NOI18N

        GroupLayout statusPanelLayout = new GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(statusPanel);
        statusPanel.setBounds(40, 40, 920, 20);

        navPanel.setBackground(new Color(0, 51, 51));
        navPanel.setName("navPanel"); // NOI18N

        GroupLayout navPanelLayout = new GroupLayout(navPanel);
        navPanel.setLayout(navPanelLayout);
        navPanelLayout.setHorizontalGroup(
            navPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        navPanelLayout.setVerticalGroup(
            navPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 760, Short.MAX_VALUE)
        );

        getContentPane().add(navPanel);
        navPanel.setBounds(967, 36, 216, 760);

        miscPanel.setBackground(new Color(0, 51, 51));
        miscPanel.setName("miscPanel"); // NOI18N

        GroupLayout miscPanelLayout = new GroupLayout(miscPanel);
        miscPanel.setLayout(miscPanelLayout);
        miscPanelLayout.setHorizontalGroup(
            miscPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        miscPanelLayout.setVerticalGroup(
            miscPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );

        getContentPane().add(miscPanel);
        miscPanel.setBounds(41, 685, 920, 111);

        scrollPane.setName("scrollPane"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 916, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        scrollPane.setViewportView(jPanel1);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(41, 67, 920, 612);

        menuBar.setName("menuBar"); // NOI18N

        jMenu1.setText("File");
        jMenu1.setName("jMenu1"); // NOI18N
        menuBar.add(jMenu1);

        jMenu2.setText("Edit");
        jMenu2.setName("jMenu2"); // NOI18N
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        pack();
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
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
