/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpv;

/**
 *
 * @author Javier Palacios
 */
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.Date;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Academia
 */
public class TPV extends JFrame implements ActionListener{       

    TPV (){
        initDate();
        // JTable -------------------------------       
        initJTable(ticketTable);
        personalizeTable(ticketTable);
        JScrollPane ticketPanel = new JScrollPane(ticketTable);
        ticketPanel.setBorder(BorderFactory.createEmptyBorder(8,8,4,4));
        ticketTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ticketTableActionPerformed(evt);
            }
        });

        // Set Control Panel ---------------------
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout(10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(4,8,8,4));

        JPanel north = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new GridLayout(4, 3, 5, 5));
        center.setBorder(BorderFactory.createEmptyBorder(0,8,0,0));        
        JPanel west = new JPanel(new GridLayout(4,1));

        north.add(totalLabel, "West");
        north.add(dateText, "East");
        initGeneralComp("Cobrar", west, "West");
        initGeneralComp("Aparcar", west, "West");
        initGeneralComp("Limpiar", west, "West");
        initGeneralComp("Salir", west, "West");
        initGeneralComp("1", center,"Center");
        initGeneralComp("2", center,"Center");
        initGeneralComp("3", center,"Center");    
        initGeneralComp("4", center,"Center");
        initGeneralComp("5", center,"Center");
        initGeneralComp("6", center,"Center");   
        initGeneralComp("7", center,"Center");
        initGeneralComp("8", center,"Center");
        initGeneralComp("9", center,"Center");
        center.add(new JLabel());
        initGeneralComp("0", center,"South");
        initGeneralComp("  Enter  " , controlPanel, "East");

        controlPanel.add(west, "West");
        controlPanel.add(center, "Center");
        controlPanel.add(north, "North");

        // Set Category Panel ---------------------
        JPanel categoryPanel = new JPanel(new GridLayout(2,3,10,10));
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(100,20,100,24));
        initGeneralComp("Carnes", categoryPanel,"");
        initGeneralComp("Pescados", categoryPanel,"");
        initGeneralComp("Refrescos", categoryPanel,"");
        initGeneralComp("Cafes", categoryPanel,"");
        initGeneralComp("Postres", categoryPanel,"");
        initGeneralComp("Licores", categoryPanel,"");

        // Set Articles Panel ---------------------
        productPanel = new JPanel(new BorderLayout());
        productPanel.setBorder(BorderFactory.createEmptyBorder(50,20,50,20));
        
        fishPanel = new JPanel(new GridLayout(2,3,10,10));
        initProducts(" 15.20","Lubina   ", fishPanel,"fish");
        initProducts(" 15.20","Bacalao  ", fishPanel,"fish");
        initProducts(" 15.20","Dorada   ", fishPanel,"fish");
        initProducts(" 15.20","Sardinill", fishPanel,"fish");
        fishPanel.add(new JLabel(), "Center");
        initProducts(" 15.20","Merluza  ", fishPanel,"fish");

        drinkPanel = new JPanel(new GridLayout(2,3,10,10));
        initProducts(" 1.20","CocaCola ", drinkPanel,"drink");
        initProducts(" 1.20","FantaNara", drinkPanel,"drink");
        initProducts(" 1.20","Acuarius ", drinkPanel,"drink");
        initProducts(" 1.20","Agua     ", drinkPanel,"drink");
        drinkPanel.add(new JLabel(), "Center");
        initProducts(" 1.20","NesTea   ", drinkPanel,"drink");

        coffePanel = new JPanel(new GridLayout(2,3,10,10));
        initProducts(" 2.50","Cortado  ", coffePanel,"coffe");
        initProducts(" 2.50","Americano", coffePanel,"coffe");
        initProducts(" 2.50","Carajillo", coffePanel,"coffe");
        initProducts(" 2.50","ConLeche ", coffePanel,"coffe");
        coffePanel.add(new JLabel(), "Center");
        initProducts(" 2.50","Capuchino", coffePanel,"coffe");

        dessertPanel = new JPanel(new GridLayout(2,3,10,10));
        initProducts(" 5.50","Helado   ", dessertPanel,"dess");
        initProducts(" 5.50","Flan     ", dessertPanel,"dess");
        initProducts(" 5.50","TartaQues", dessertPanel,"dess");
        initProducts(" 5.50","Fruta    ", dessertPanel,"dess");
        dessertPanel.add(new JLabel(), "Center");
        initProducts(" 5.50","Natillas  ", dessertPanel,"dess");

        licorsPanel = new JPanel(new GridLayout(2,3,10,10));
        initProducts(" 4.00","Wisky    ", licorsPanel,"licor");
        initProducts(" 4.00","Hierbas  ", licorsPanel,"licor");
        initProducts(" 4.00","Ron      ", licorsPanel,"licor");
        initProducts(" 4.00","Brandy   ", licorsPanel,"licor");
        licorsPanel.add(new JLabel(), "Center");
        initProducts(" 4.00","Puert.Ind", licorsPanel,"licor");
               
        meatPanel = new JPanel(new GridLayout(2,3,10,10));
        initProducts(" 20.40","Entrecot", meatPanel,"meat");
        initProducts(" 20.40","Solomillo", meatPanel,"meat");
        initProducts(" 20.40","Carpaccio", meatPanel,"meat");
        initProducts(" 20.40","Lomo     ", meatPanel,"meat");
        meatPanel.add(new JLabel());
        initProducts(" 20.40","Chuleton ", meatPanel,"meat");
        
        productPanel.add(previous);
        
        // FRAME STRUCTURE ---------------------
        setLayout(new GridLayout(2,2));
        add(ticketPanel);
        add(categoryPanel);   
        add(controlPanel);   
        add(productPanel);   

        setTitle("TPV");
        setIconImage(new ImageIcon(getClass().getResource("img/tpv.png")).getImage());
        setSize(500,500);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                LogGen1.info("Session ended");
                exit();
            }
        });
        
        LogGen1.info("Session started succesfully");
    }

    private void initDate(){
        dateText.setBackground(Color.black);
        dateText.setForeground(Color.green);

        timer = new Timer(1000, (ActionEvent e) -> {
            dateText.setText(timeFormat.format(new Date()));
        });
        timer.setInitialDelay(0);
        timer.start(); 
    }

    private void initJTable(JTable table){
        table.setModel(new DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Articulo", "Unidades", "Precio \u20ac", "Total \u20ac", "DECR", "DEL"
            }
        ));
        model = (DefaultTableModel) table.getModel();
    }

    private void personalizeTable(JTable table){
        table.getColumnModel().getColumn(4).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(5);
    
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(Color.yellow);
        cellRenderer.setToolTipText("Reducir cantidad en 1");
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setBackground(Color.red);
        cellRenderer.setToolTipText("Eliminar fila");
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
    }
    
    //For general buttons
    private void initGeneralComp(String name, JPanel panel, String layout){
        ImageIcon icon = new ImageIcon("img/"+name+".png");
        JButton btn = new JButton(name, icon);
        btn.addActionListener(this);
        panel.add(btn, layout);

        if(Arrays.asList(categories).contains(name)){
            TitledBorder titled = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),name, 3, 4);
            titled.setTitleColor(Color.white);
            btn.setBorder(titled);
        }
    }
    
    //For producs buttons
    private void initProducts(String price, String name, JPanel panel, String iconName){
        ImageIcon icon = new ImageIcon("img/"+iconName+".png");
        //price is passed with the name to be avaiable for the table
        JButton btn = new JButton(name+price, icon);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);  
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.addActionListener(this);
        panel.add(btn);
    }

    // ////////////////////////////////////////////////////////////////////////
    // EVENTS
    // ////////////////////////////////////////////////////////////////////////
    public void actionPerformed (ActionEvent e){
        String ac = e.getActionCommand();
        LogGen1.info("Action Performed: "+ac);

        if(ac.equals("Carnes")) {showProductCategory(meatPanel);}
        else if(ac.equals("Pescados")) {showProductCategory(fishPanel);}
        else if(ac.equals("Refrescos")) {showProductCategory(drinkPanel);}
        else if(ac.equals("Cafes")) {showProductCategory(coffePanel);}
        else if(ac.equals("Postres")) {showProductCategory(dessertPanel);}
        else if(ac.equals("Licores")) {showProductCategory(licorsPanel);}
        // ---------------------------------------------------------------------
        else if(ac.equals("Cobrar")) {if(numRows>0)ticketGen();}
        else if((ac.equals("Aparcar") || ac.equals("Recup."))) {park(ac, e);}
        else if(Arrays.asList(numPad).contains(ac)){if(numRows>0)changeQuantity(ac);}
        else if(ac.equals("Limpiar")) {clean();}
        // ---------------------------------------------------------------------
        else if(ac.equals("Salir")) {exit();}
        else {addProduct(ac);}
    }

    private void showProductCategory (JPanel currentPanel){
        productPanel.remove(previous);
        previous = currentPanel;
        productPanel.add(currentPanel, "Center");
        currentPanel.setVisible(false);
        currentPanel.setVisible(true);
    }

    private void addProduct(String s){
        numRows = ticketTable.getRowCount();
        String str = s.substring(0,s.lastIndexOf(" "));
        // #1.00
       boolean inTable = false;
       for(int i = 0; i < numRows; i++ ){
            if(str.equals(ticketTable.getValueAt(i, 0))){
                ticketTable.setRowSelectionInterval(i,i);
                setRowVal(i, 1);
                inTable = true;
            }
        }
       
        if (!inTable){
            double price = Double.parseDouble(s.substring(s.lastIndexOf(" ")+1));
            Object[] newRecord = {str, 1, price, price, "-1", "X"};
            model.addRow(newRecord);
            ticketTable.setRowSelectionInterval(numRows, numRows);
            getTotal(++numRows);
        }
    }

    private void ticketTableActionPerformed(java.awt.event.MouseEvent evt){
        // #4.00
        int selctRow = ticketTable.getSelectedRow();
        if(ticketTable.getSelectedColumn()==4){
            setRowVal(selctRow, -1);

            if(Integer.parseInt(ticketTable.getValueAt(selctRow, 1).toString()) == 0){
                model.removeRow(selctRow);
                --numRows;
            }
        } else if (ticketTable.getSelectedColumn()==5){
            model.removeRow(selctRow);
            --numRows;
        }
    }

    private void changeQuantity(String num){
        int selctRow = ticketTable.getSelectedRow();

        if (!num.equals("  Enter  " ) && selctRow >= 0)  {
            newQuantity += num;
            ticketTable.setValueAt(newQuantity, selctRow, 1); 
        } else {
            newQuantity = "";
            setRowVal(selctRow, 0);
            ticketTable.removeRowSelectionInterval(0,0);
        }
    }

    private void setRowVal(int i, int plus){
        int qty = plus + Integer.parseInt(ticketTable.getValueAt(i, 1).toString());
        double price = (double) ticketTable.getValueAt(i, 2); 
                
        ticketTable.setValueAt(qty, i,1);
        ticketTable.setValueAt(df.format(qty*price), i,3);
        getTotal(numRows);
    }

    private void getTotal(int numRows){
        t = 0.0;
        for(int i = 0; i < numRows; i++ ){
            t += Double.parseDouble(ticketTable.getValueAt(i, 3).toString().replace(",", "."));
        }
        totalLabel.setText("    Total: "+df.format(t)+" \u20ac");
    }

    private void clean(){
        if(JOptionPane.showConfirmDialog(this, "Limpiar ticket?", "Ticket", 2, 1, tpv) == 0){
            model.setRowCount(0); 
            numRows = 0;
        } 
    }

    private void ticketGen(){

        String ticket = tableItinerator(0, "");

        String finalTicket = " Cif: A80192727\n Bar la Pedruscada\n Hora/Fecha: " +
                   timeFormat.format(new Date()) +"\n\n"+ "Producto \tCant. \tPrecio \tTotal "+
                   "\n----------------------------------------------------------------------------"+"\n"+
                   ticket  + "\n----------------------------------------------------------------------------"+
                  "\n\t\t Iva: \t21%"+"\n\t\t Base: \t"+ df.format(t * 0.79)+"\u20ac" +"\n\t\t Tot.: \t" + df.format(t)+"\u20ac";

        if(JOptionPane.showConfirmDialog(this, new JTextArea(finalTicket), "Ticket", 2, 1, tpv) == 0){
            CreateFile.path("ticket/ticket.txt").print(finalTicket,1);
            model.setRowCount(0);
            numRows = 0;
            totalLabel.setText("    Total: 0.00 \u20ac");
        }
    }

    private void park(String s, ActionEvent e){
        btn = (JButton) e.getSource();
        // #5.00
        if(s.equals("Aparcar") && numRows > 0){
            initJTable(parkedTable);
            tableItinerator(1,"");

            model = (DefaultTableModel) ticketTable.getModel();
            model.setRowCount(0);
            btn.setText("Recup.");
            btn.setIcon(new ImageIcon("img/Recup.png"));

            numRows = 0;
            parkedTicket = true;
            totalLabel.setText("    Total: 0.0 \u20ac");

        } else if (s.equals("Recup.") ){
            recupTicket();
        }
    }

    private String tableItinerator (int park, String ticket){
        for(int i = 0; i < numRows; i++ ){
            String product = (String)ticketTable.getValueAt(i, 0);            
            int qty = (int)ticketTable.getValueAt(i, 1);            
            double price = (double)ticketTable.getValueAt(i, 2);
            Double p = Double.parseDouble(ticketTable.getValueAt(i, 3).toString().replace(",", "."));

            if (park == 1){
                Object[] newRecord = { product, qty, price, p, "-1", "X" };
                model.addRow(newRecord);       
            } else {
                ticket = ticket+  product + "\t" + qty + "\t" +df.format(price)+ "\u20ac" + "\t" + df.format(p) +"\u20ac" + "\n";
            }
        }
        return ticket;
    }
    
    private int recupTicket(){
        if(numRows > 0 && 
            JOptionPane.showConfirmDialog(this, "Recuperar y perder el ticket actual?", "Recuperar", 2, 1, tpv) == 2
        ){ return 2;}
        ticketTable.setModel(parkedTable.getModel());
        model =  (DefaultTableModel) ticketTable.getModel();
        personalizeTable(ticketTable);
        btn.setText("Aparcar");
        btn.setIcon(new ImageIcon("img/Aparcar.png"));

        numRows = ticketTable.getRowCount();
        parkedTicket = false;
        getTotal(numRows);
        return 0;
    }
    
    private void exit(){        
        if(parkedTicket){
            int exit = JOptionPane.showConfirmDialog(this, "Tienes tickets en curso. Guardar los cambios?", "Salir", 1, 1, tpv);
            if(exit == 1) {System.exit(0);} //no
            else if(exit == 0) {//yes
                if(recupTicket() == 0){
                    ticketGen();
                    return;
                }
            }
        } 

        if (numRows > 0 ){
            int exit = JOptionPane.showConfirmDialog(this, "Guardar los cambios?", "Salir", 1, 1, tpv);
            if(exit == 1) {System.exit(0);} //no
            else if(exit == 0) {//yes
                ticketGen();
            }
        } else {System.exit(0);}
    }

    // ////////////////////////////////////////////////////////////////////////
    // MAIN
    // ////////////////////////////////////////////////////////////////////////
    public static void main(String args[]) {
        
        LogGen1.start();
        //Set look & feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {LogGen1.error(ex.getMessage());}
        catch (InstantiationException ex) {LogGen1.error(ex.getMessage());}
        catch (IllegalAccessException ex) {LogGen1.error(ex.getMessage());}
        catch (javax.swing.UnsupportedLookAndFeelException ex) {LogGen1.error(ex.getMessage());}
        
        // Crete and launch the Frame. Grab any other possyble error 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    new TPV().setVisible(true);
                } catch(Throwable e){LogGen1.error(e.getMessage());}
            }
        });
    }

    // Variable declaration
    ImageIcon tpv = new ImageIcon("img/tpv.png");
    private JTable ticketTable = new JTable();
    private JTable parkedTable  = new JTable();
    private int numRows;
    private DefaultTableModel model;
    private DefaultTableModel modelp;

    private Timer timer;
    private JTextField dateText = new JTextField();
    private DateFormat timeFormat = new SimpleDateFormat("  kk:mm:ss  dd/MM/yyyy  ");  
    
    private DecimalFormat df = new DecimalFormat("#.00");
    private JLabel totalLabel = new JLabel("    Total: 0.0 \u20ac");

    private JPanel productPanel;
    private JPanel meatPanel;        
    private JPanel fishPanel;
    private JPanel drinkPanel;
    private JPanel coffePanel;
    private JPanel dessertPanel;
    private JPanel licorsPanel;
    private JPanel previous = new JPanel(); 

    private String [] categories = {"Carnes", "Cafes", "Pescados", "Refrescos", "Postres", "Licores"};
    private String [] numPad = {"0","1","2","3","4","5","6","7","8","9","  Enter  " };
    private String newQuantity = "";

    private double t;

    private boolean parkedTicket = false;
    private JButton btn;
}