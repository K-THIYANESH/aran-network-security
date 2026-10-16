import java.io.FileWriter;
import java.io.IOException;

public class LoggerService {

    public static void log(String message)
    {

        try
        {

            java.io.File dir = new java.io.File("logs");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileWriter fw = new FileWriter("logs/security.log",true);

            fw.write(message+"\n");

            fw.close();

        }

        catch(IOException e)
        {

        }

    }

}