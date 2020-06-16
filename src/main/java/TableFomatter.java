import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class TableFomatter {
    ReportConfig config;
    String defaultDelimiter = "\t";



    public TableFomatter(ReportConfig config) {
        this.config = config;
    }

    public String getDefaultDelimiter() {
        return defaultDelimiter;
    }

    public void setDefaultDelimiter(String defaultDelimiter) {
        this.defaultDelimiter = defaultDelimiter;
    }

    public void format(InputStream inputStream, OutputStream outputStream) throws IOException {
        String currentline ;
        String allXml;

        BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
        currentline = reader.readLine();
        while (currentline != null){
            StringTokenizer currentStringTokenazer  = new StringTokenizer(currentline, defaultDelimiter, false);
            ArrayList<String> list = new ArrayList<String>();
            while (currentStringTokenazer.hasMoreTokens()){
                list.add(currentStringTokenazer.nextToken());
            }
            // TODO createStrings



        }



    }

}
