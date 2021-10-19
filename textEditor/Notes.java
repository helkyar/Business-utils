// import java.io.*;
// import java.awt.*;
import java.util.*;

import javax.security.auth.callback.Callback;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

class Notes extends JFrame implements ActionListener{

    private JMenuBar mBar;
    private JToolBar tBar;
    private JPopupMenu pMenu;

    private JScrollPane Spanel;
    private JTextArea text;

    // ====================================================
    // CONSTRUCTOR
    // ====================================================
    Notes(){
        super("Notes");
        // MENU BAR ----------------------------------------------
        JMenu file = new JMenu("Archivo");
        createComponent("Guardar", file);
        createComponent("Nuevo", file);
        createComponent("Abrir", file);
        createComponent("Imprimir", file);

        JMenu edit = new JMenu("Editar");
        createComponent("Cut", edit);
        createComponent("Copy", edit);
        createComponent("Paste", edit);

        JMenu format = new JMenu("Formato");
        createComponent("Basica", format);
        createComponent("Negrita", format);
        createComponent("Cursiva", format);
        createComponent("Subrayado", format);
        format.addSeparator();
        createComponent("Alinear Izquierda", format);
        createComponent("Alinear Derecha", format);
        createComponent("Centrar", format);
        createComponent("Justificar", format);

        JMenu others = new JMenu("Otros");
        createComponent("Cerrar", others);
        createComponent("Ayuda", others);

        mBar = new JMenuBar();
        mBar.add(file);
        mBar.add(edit);
        mBar.add(format);
        mBar.add(others);

        // SCROLLING TEXT AREA ------------------------------------
        text = new JTextArea();
        Spanel = new JScrollPane(text);

        // STRUCTURING THE FRAME ----------------------------------
        setJMenuBar(mBar);
        add(Spanel);

        // --------------------------------------------------------
        setSize(500, 500);
        setVisible(true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                close();
                }
            });
    }

    // COMPONENT CREATOR ===============================================================
    private void createComponent ( String s, JMenu m){
        JMenuItem mItem = new JMenuItem(s);
        mItem.addActionListener(this);
        m.add(mItem);
    }

    // CLOSE =============================================================================
    private void close (){
        int exit = JOptionPane.showConfirmDialog(this, "Guardar los cambios?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION);
        if(exit == 1) {System.exit(0);} //no
        else if(exit == 0) {//yes
            // save("close");
        } 
    }

    // EVENT LISTENER =====================================================================
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        // File --------------------------------------------------------------------------
        if(s.equals("Cerrar")) close();

        // Edit --------------------------------------------------------------------------
          if (s.equals("cut")) { 
            text.cut();
        } else if (s.equals("copy")) {
            text.copy();
        } else if (s.equals("paste")) {
            text.paste();
        }
        // Format ------------------------------------------------------------------------
        // Help --------------------------------------------------------------------------
    }

    public static void main(String[] args) {
        new Notes();
    }
}