import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class Main {


    public static void main(String[] args) throws IOException {

        if (args.length == 3) {
            String address = args[0];
            ConfigParser configParser = new ConfigParser();
            ReportConfig reportConfig = configParser.parseFromAddress(address);
            TableFomatter tableFomatter = new TableFomatter(reportConfig);
            FileInputStream fileInputStream = new FileInputStream(args[1]);
            FileOutputStream fileOutputStream = new FileOutputStream(args[2]);


            tableFomatter.format(fileInputStream, fileOutputStream);


            fileOutputStream.close();


        } else {
            System.out.println("Somerthing is Wrong");
        }


    }
}
