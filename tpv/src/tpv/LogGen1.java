package tpv;

/*
 * Get version, SO & Acces route
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

/**
 *
 * @author Javier Palacios
 */
public class LogGen1 {
private static String line = "-----------------------------------------------------------------------------\n";
private static String name;
private static String header;
//private String abspath = getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm();

public static void start() {
    //Get user name
    String s = Cmd.runThr("whoami", "\\");
//    s = s.substring(0, s.length()- 1);
    String os = System.getProperty("os.name").toLowerCase();
    String exepath = System.getProperty("user.dir");
    
    String msg = "User: " + s + ". Session start attempt" + "\n\t\t\t\t\tOS: " 
                 + os + ". Path: " + exepath; 
    
    try {
        //Date format for the file
        DateFormat logName = new SimpleDateFormat ("dd-MM-yyyy");
        name = "log/" + "log" +"("+ logName.format(new Date()) +")"+ ".log";
            
            File f = new File("log");
            f.mkdir();  //creates folder if none exists
        } catch (Exception ex) {
            System.out.println(er);
        }
            writer(line,"\tSession\t\t", msg);
    }

    public static void error(String error){
            writer("", "\tError\t\t", error);
    }

    public static void info(String info){
            writer("","\tInfo\t\t", info);
    }
    
    //Write at specified file
    public static void writer(String s, String type, String msg){
        try {
            DateFormat session = new SimpleDateFormat ("dd/MM/yyyy kk:mm:ss");
            header = s + session.format(new Date()) + type;

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(name,true));

            PrintWriter fileWriter = new PrintWriter(bufferedWriter);
            fileWriter.print(header);
            fileWriter.print(msg + "\n");

            fileWriter.close();
            fileWriter.flush();

        } catch (Exception ex) {
            System.out.println(er);
        }
    }
    private static String er = "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠛⢉⢉⠉⠉⠻⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⠟⠠⡰⣕⣗⣷⣧⣀⣅⠘⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⠃⣠⣳⣟⣿⣿⣷⣿⡿⣜⠄⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⡿⠁⠄⣳⢷⣿⣿⣿⣿⡿⣝⠖⠄⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⠃⠄⢢⡹⣿⢷⣯⢿⢷⡫⣗⠍⢰⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⡏⢀⢄⠤⣁⠋⠿⣗⣟⡯⡏⢎⠁⢸⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⠄⢔⢕⣯⣿⣿⡲⡤⡄⡤⠄⡀⢠⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⠇⠠⡳⣯⣿⣿⣾⢵⣫⢎⢎⠆⢀⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⠄⢨⣫⣿⣿⡿⣿⣻⢎⡗⡕⡅⢸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⠄⢜⢾⣾⣿⣿⣟⣗⢯⡪⡳⡀⢸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⠄⢸⢽⣿⣷⣿⣻⡮⡧⡳⡱⡁⢸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⡄⢨⣻⣽⣿⣟⣿⣞⣗⡽⡸⡐⢸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⡇⢀⢗⣿⣿⣿⣿⡿⣞⡵⡣⣊⢸⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⡀⡣⣗⣿⣿⣿⣿⣯⡯⡺⣼⠎⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣧⠐⡵⣻⣟⣯⣿⣷⣟⣝⢞⡿⢹⣿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⡆⢘⡺⣽⢿⣻⣿⣗⡷⣹⢩⢃⢿⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣷⠄⠪⣯⣟⣿⢯⣿⣻⣜⢎⢆⠜⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⡆⠄⢣⣻⣽⣿⣿⣟⣾⡮⡺⡸⠸⣿⣿⣿⣿\n" +
                        "⣿⣿⡿⠛⠉⠁⠄⢕⡳⣽⡾⣿⢽⣯⡿⣮⢚⣅⠹⣿⣿⣿\n" +
                        "⡿⠋⠄⠄⠄⠄⢀⠒⠝⣞⢿⡿⣿⣽⢿⡽⣧⣳⡅⠌⠻⣿\n" +
                        "⠁⠄⠄⠄⠄⠄⠐⡐⠱⡱⣻⡻⣝⣮⣟⣿⣻⣟⣻⡺⣊";
}