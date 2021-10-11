import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

public class CreateFile {

public static void imprimir(String name, String msg) {
        try {
            Date now = new Date();
            DateFormat df = new SimpleDateFormat ("dd-MM-yyyy kk.mm.ss");
    
            //  name = "tickets/" + name  + ".txt";
            name = "tickets/" + name +"("+ df.format(now) +")"+ ".txt";

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(name));

            PrintWriter fileWriter = new PrintWriter(bufferedWriter);
            fileWriter.print(msg);

            fileWriter.close();
            fileWriter.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}