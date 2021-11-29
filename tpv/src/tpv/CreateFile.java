/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpv;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Javier Palacios Botejara
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
 * Creates specified files in specified path either statically or instanciated
 * @author Javier Palacios
 */
public class CreateFile {
    public static String route;
    private String road;

    CreateFile(){}
    
    CreateFile(String road){this.road = road;}

    public static CreateFile path(String route){
        CreateFile.route = route;
        return new CreateFile();
    }

    public static void print(String msg, int format) {
        new CreateFile().inputWriter(route, format, msg);
    }

    public void write(String msg, int df){
        inputWriter(road,df,msg);
    }

    public void inputWriter(String dir, int format, String msg){
        try {
            String prefix;
            if (format == 1){
                DateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
                prefix = "("+ df.format(new Date()) +")";

            } else if (format == 2) {
                DateFormat df = new SimpleDateFormat ("dd-MM-yyyy kk.mm.ss");
                prefix = "("+ df.format(new Date()) +")";

            } else  {prefix = "";}
            
            String shit;
            dir = dir.substring(0,dir.indexOf(".")) + prefix + dir.substring(dir.indexOf("."));
            if(dir.lastIndexOf("/")!=-1){shit = dir.substring(0,dir.lastIndexOf("/"));}
            else {shit = dir;}
         
            File f = new File(shit);
            f.mkdirs(); 

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dir));

            PrintWriter fileWriter = new PrintWriter(bufferedWriter);
            fileWriter.print(msg);

            fileWriter.close();
            fileWriter.flush();
            
        } catch (IOException ex) {ex.printStackTrace();}
    }
}
