
import java.util.*;
import javax.swing.*;

import javax.swing.text.StyledEditorKit;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class Notes extends JFrame implements ActionListener{

    private JMenuBar mBar;
    private JToolBar tBar;
    private JPopupMenu pMenu;

    private JScrollPane Spanel;
    // private JTextArea text; //StyledEditorKit doesn't work on textAreas
    private JTextPane text;

    // ====================================================
    // CONSTRUCTOR
    // ====================================================
    Notes(){
        super("Notes");

        tBar = new JToolBar();

        JMenu file = new JMenu("Archivo");
        createComponent("Guardar", file);
        createComponent("Nuevo", file);
        createComponent("Abrir", file);
        createComponent("Imprimir", file);

        JMenu edit = new JMenu("Editar");
        createComponent("Cortar", edit);
        createComponent("Copiar", edit);
        createComponent("Pegar", edit);

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
        text = new JTextPane();
        Spanel = new JScrollPane(text);

        // STRUCTURING THE FRAME ----------------------------------
        setLayout(new BorderLayout());
        setJMenuBar(mBar);
        add(Spanel, BorderLayout.CENTER);
        add(tBar, BorderLayout.NORTH);

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

    // =================================================================================
    // COMPONENT CREATOR ===============================================================
    private void createComponent ( String s, JMenu m){

        ImageIcon icon = new ImageIcon("img/"+"placeholder"+".png");
        ActionListener action = this;

        // Special cases
        if(s.equals("Negrita")) {action = new StyledEditorKit.BoldAction();}
        else if(s.equals("Cursiva")) {action = new StyledEditorKit.ItalicAction();}
        else if(s.equals("Subrayado")) {action = new StyledEditorKit.UnderlineAction();}
        else if(s.equals("Alinear Izquierda")) {}
        else if(s.equals("Alinear Derecha")) {}
        else if(s.equals("Centrar")) {}
        else if(s.equals("Justificar")) {}

        JMenuItem mItem = new JMenuItem(s, icon);
        mItem.addActionListener(action);
        m.add(mItem);

        JButton buton = new JButton(icon);
        buton.addActionListener(action);
        tBar.add(buton);
    }

    // OPEN ============================================================================
    private void open() {
        // Creamos el objeto para abrir archivos
        JFileChooser j = new JFileChooser("f:");
        
        // invocamos el dialogo de abertura archivo
        int r = j.showOpenDialog(this);
        
        // If the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            // etiqueta de el directorio completo                
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            try {
                // String
                String s1 = "", sl = "";
                
                // lector de archivo
                FileReader fr = new FileReader(fi);
                
                // Buffered lector
                BufferedReader br = new BufferedReader(fr);

                // inicializa sl
                sl = br.readLine();
                
                // leer desde el archivo
                while ((s1 = br.readLine()) != null) {   
                    sl = sl + "\n" + s1;
                }
                    
                // Set the text
                text.setText(sl);
            } catch (Exception evt) {JOptionPane.showMessageDialog(this, evt.getMessage());}    

        } else {// Usuario cancela operacion
            JOptionPane.showMessageDialog(this, "Operacion cancelada");
        }
    }

    // SAVE =============================================================================
    private void save(String s) {
         // Creatmos el objecto de JFileChooser class
            JFileChooser j = new JFileChooser("f:");
            
            // invocamos showsSaveDialog function para guardar
            int r = j.showSaveDialog(this); //guardar dialogo
            
            if (r == JFileChooser.APPROVE_OPTION) {
                // etiqueta de el directorio completo
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try {
                    // Creamos file writer
                    FileWriter wr = new FileWriter(fi, false);
                    
                    // bufer para ecribir
                    BufferedWriter w = new BufferedWriter(wr);
                    
                    // escribe el archivo
                    w.write(text.getText());
                    w.flush();
                    w.close(); 

                    // Operación realizada con éxito, sale del bucle
                    if (s.equals("close")) System.exit(0);

                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Operacion cancelada");
                if (s.equals("close")) close();
                else if ( s.equals("newDoc")) newDoc();
            }
    }

    // CLOSE =============================================================================
    private void newDoc (){
        int newD = JOptionPane.showConfirmDialog(this, "Guardar los cambios?", "Nuevo Documento", JOptionPane.YES_NO_CANCEL_OPTION);
        if(newD == 1) {text.setText("");} //no
        else if(newD == 0) {//yes
            save("newDoc");
            text.setText("");
        } 
    }

    // NEW DOCUMENT =============================================================================
    private void close (){
        int exit = JOptionPane.showConfirmDialog(this, "Guardar los cambios?", "Salir", JOptionPane.YES_NO_CANCEL_OPTION);
        if(exit == 1) {System.exit(0);} //no
        else if(exit == 0) {//yes
            save("close");
        } 
    }

    // EVENT LISTENER =====================================================================
    public void actionPerformed(ActionEvent e) {
        String ac = e.getActionCommand();
        String s = e.getSource().toString();

        // File --------------------------------------------------------------------------
        if(s.equals("Cerrar")) close();
        else if (ac.equals("Guardar"))  {save("");}
        else if (ac.equals("Nuevo"))    {newDoc();}
        else if (ac.equals("Abrir"))    {open();}
        else if (ac.equals("Imprimir")) {
            try {
                text.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(this, evt.getMessage());
            }
        }

        // Edit --------------------------------------------------------------------------
        if (ac.equals("Cortar")) {text.cut();}
        else if (ac.equals("Copiar")) {text.copy();}
        else if (ac.equals("Pegar")) {text.paste();}

        // Format ------------------------------------------------------------------------
        if(s.equals("Negrita")){}
        if(s.equals("Cursiva")){}
        if(s.equals("Subrayado")){}
        if(s.equals("Alinear Izquierda")){}
        else if(s.equals("Alinear Derecha")){}
        else if(s.equals("Centrar")){}
        else if(s.equals("Justificar")){}

        // Help --------------------------------------------------------------------------
    }

    public static void main(String[] args) {
        new Notes();
    }
}