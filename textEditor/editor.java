
/**
 *
 * @author Admin
 */
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.*;
import javax.swing.text.*; 

/*El codigo realiza un notepad */
class editor extends JFrame implements ActionListener {

    // Text component
    JTextArea t;

    // Scroll component
    JScrollPane jp;

    // Frame
    JFrame f;
    public Font arial=new Font("Arial", Font.ITALIC, 15);
    public Font verdana = new Font("Verdana", Font.BOLD, 12);
    public Font courier = new Font ("Courier New", 1, 11);
     
    
    // Constructor
    editor() {
        // Creamos la ventana
        f = new JFrame("Editor de Texto de Javi (apropiado de Melany)");
        f.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SalirApp();
                }
            });
        
        try {
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            // Set theme to ocean
            f.setDefaultCloseOperation(EXIT_ON_CLOSE);
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }
        
        // Text componente
        t = new JTextArea();
        
        // Barra de menu
        JMenuBar mb = new JMenuBar();
        
        // Creamos menu menu
        JMenu m1 = new JMenu("File");
        
        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");
        
        // controla el jMenuItem de arriba si abro open abre algo. 
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);
        
        // se crea el menu
        JMenu m2 = new JMenu("Edit");
        
        // Create menu items
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");
        
        // agregar action listener
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        
        JMenu m3 = new JMenu("font");//para cambiar la fuente
         // Create menu items
         
        JMenuItem mi8 = new JMenuItem("Arial");
        JMenuItem mi10 = new JMenuItem("Courier");
        JMenuItem mi11 = new JMenuItem ("verdana");
        
        mi8.addActionListener(this); //para que haga la accion
        mi10.addActionListener(this);
        mi11.addActionListener(this);
        m3.add(mi8); 
        m3.add(mi10);
        m3.add(mi11);
       
        JMenuItem mc = new JMenuItem("close"); //se le agrega la accion
        mc.addActionListener(this);

        // Agregarlo todo a la barra
        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(mc);
        
        f.setJMenuBar(mb); 
        f.add(t);//agrega el text area
        jp = new JScrollPane(t);
        f.add(jp); 
        f.setLocationRelativeTo(null);
        f.setSize(500, 500); //Tamaño base del frame
        f.setExtendedState(MAXIMIZED_BOTH);
        f.setVisible(true); 
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand(); //variable:mete todas las variables de los comandos
        
        if (s.equals("cut")) { //le damos la accion que debe realizar la accion cut
            t.cut();
        } else if (s.equals("copy")) {
            t.copy();
        } else if (s.equals("paste")) {
            t.paste();
        } else if (s.equals("Save")) {
            // Creatmos el objecto de JFileChooser class
            JFileChooser j = new JFileChooser("f:");
            
            // invocamos showsSaveDialog function para guardar
            int r = j.showSaveDialog(null); //guardar dialogo
            
            if (r == JFileChooser.APPROVE_OPTION) {
                // etiqueta de el directorio completo
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try {
                    // Creamos file writer
                    FileWriter wr = new FileWriter(fi, false);
                    
                    // bufer para ecribir
                    BufferedWriter w = new BufferedWriter(wr);
                    
                    // escribe el archivo
                    w.write(t.getText());
                    w.flush();
                    w.close(); 
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(f, "el usuario cancela");
            }
        } else if (s.equals("Print")) {
            try {
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            // Creamos el objeto para abrir archivos
            JFileChooser j = new JFileChooser("f:");
            
            // invocamos el dialogo de abertura archivo
            int r = j.showOpenDialog(null);
            
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
                    t.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } // Usuario cancela operacion
            else {
                JOptionPane.showMessageDialog(f, "El usuario ha cancelado la operacion");
            }
           
        } else if (s.equals("New")) {
            t.setText("");
        } else if (s.equals("close")) {
           
            SalirApp(); 
            //si es tal letra seleaccionada muestrame tal fuente
        } else if (s.equals("Arial")) { 
            t.setFont(arial);
        } else if (s.equals("Courier")){
            t.setFont(verdana);
        } else if (s.equals("verdana")){
            t.setFont(courier);
        }
        
    }
    public void Save (){
  
            // Creamos el objecto de JFileChooser class
            JFileChooser j = new JFileChooser("f:");
            
            // invocamos showsSaveDialog funcion para guardar
            int r = j.showSaveDialog(null); //guardar dialogo
            
            if (r == JFileChooser.APPROVE_OPTION) {
                // etiqueta de el directorio completo
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                
                try {
                    // Creamos file writer
                    FileWriter wr = new FileWriter(fi, false);
                    
                    // bufer para ecribir
                    BufferedWriter w = new BufferedWriter(wr);
                    
                    // escribe el archivo
                    w.write(t.getText());
                    w.flush();
                    w.close(); //todo elemento abierto se ha de cerrar
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(f, "el usuario cancela");
            }
    }
    public void Guardar(){
       // Creatmos el objecto de JFileChooser class
            JFileChooser j = new JFileChooser("f:");
        // invocamos showsSaveDialog function para guardar
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                // etiqueta de el directorio completo
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    // Creamos file writer
                    FileWriter wr = new FileWriter(fi, false);
                    // bufer para ecribir
                    BufferedWriter w = new BufferedWriter(wr);
                   // escribe el archivo
                    w.write(t.getText());
                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } // el usuario cancela
            else {
                JOptionPane.showMessageDialog(f, "El usuario cancela");
            } 
    }
public void SalirApp(){
    try{
   if (JOptionPane.showConfirmDialog(rootPane,"¿Deseas guardar antes de cerrar?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        Guardar();
        
    } else { System.exit(0);
    
    }
    }catch (Exception evt){
        JOptionPane.showMessageDialog(f, evt.getMessage());     
    }
}
    public static void main(String args[]) {
        new editor();
    }
}
