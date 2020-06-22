import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class Main {


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {


        /*
        if (args.length == 3) {
            String addressOfConfig;
            //addressOfConfig = args[0]; // in result project
            addressOfConfig = "settings.xml"; // in now to test it

            ConfigParser configParser = new ConfigParser();
            ReportConfig reportConfig = configParser.parseFromAddress(addressOfConfig);
            TableFormatter tableFormatter = new TableFormatter(reportConfig);


            // FileInputStream fileInputStream = new FileInputStream(args[1]);               //  in result project
            FileInputStream fileInputStream = new FileInputStream("source-data.tsv"); // in now to test it


            //FileOutputStream fileOutputStream = new FileOutputStream(args[2]);
            FileOutputStream fileOutputStream = new FileOutputStream("output.txt");


            tableFormatter.format(fileInputStream, fileOutputStream);


            fileOutputStream.close();


        } else {
            System.out.println("Somerthing is Wrong");
        }
        */

        String addressOfConfig;
        //addressOfConfig = args[0]; // in result project
        addressOfConfig = "settings.xml"; // in now to test it

        ConfigParser configParser = new ConfigParser();
        ReportConfig reportConfig = configParser.parseFromAddress(addressOfConfig);
        TableFormatter tableFormatter = new TableFormatter(reportConfig);


        // FileInputStream fileInputStream = new FileInputStream(args[1]);               //  in result project
        FileInputStream fileInputStream = new FileInputStream("source-data.tsv"); // in now to test it


        //FileOutputStream fileOutputStream = new FileOutputStream(args[2]);
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt",true);


        tableFormatter.format(fileInputStream, fileOutputStream);


        fileOutputStream.close();




    }
}
