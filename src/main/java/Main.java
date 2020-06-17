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

        if (args.length == 3) {
            String addressOfConfig = args[0];
            ConfigParser configParser = new ConfigParser();
            ReportConfig reportConfig = configParser.parseFromAddress(addressOfConfig);
            TableFormatter tableFormatter = new TableFormatter(reportConfig);
            FileInputStream fileInputStream = new FileInputStream(args[1]);
            FileOutputStream fileOutputStream = new FileOutputStream(args[2]);


            tableFormatter.format(fileInputStream, fileOutputStream);


            fileOutputStream.close();


        } else {
            System.out.println("Somerthing is Wrong");
        }


    }
}
