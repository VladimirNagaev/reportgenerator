import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class TableFormatter {
    ReportConfig config;
    String defaultDelimiter = "\t";


    public TableFormatter(ReportConfig config) {
        this.config = config;
    }

    public String getDefaultDelimiter() {
        return defaultDelimiter;
    }

    public String stringDelimiter() {
        int pageWidth = config.getWidth();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < pageWidth; i++) {
            stringBuilder.append('-');
        }
        return stringBuilder.toString();
    }

    public String headRawWriter(List<ColumnConfig> columnConfigs) {
        StringBuilder headerString = new StringBuilder("");
        headerString.append("|");

        for (int i = 0; i < columnConfigs.size(); i++) {
            StringBuilder cs = new StringBuilder("");
            cs.append(" ");
            cs.append(columnConfigs.get(i).getTitle());
            cs.append(" ");
            for (int j = cs.length(); j < columnConfigs.get(i).getWidth() - 1; j++) {
                cs.append(" ");
            }
            cs.append("|");
            headerString.append(cs);
        }


        return headerString.toString();
    }

    public String rowWriter(List<ColumnConfig> columnConfigs, List<String> thisRow) {
        StringBuilder thisString = new StringBuilder();


        return thisString.toString();
    }

    public void setDefaultDelimiter(String defaultDelimiter) {
        this.defaultDelimiter = defaultDelimiter;
    }

    public int stringDivider(String string, int width) { // divides names for parts to write in into stream
        // this void will chsnge inputed string to " Иван * ов Ки * рилл" where * is row delimiter
        int stringCounter = 1;
        List<String> currentName = new ArrayList<String>();
        StringTokenizer currentNameTokenazer = new StringTokenizer(string, " ", false);
        while (currentNameTokenazer.hasMoreTokens()) {
            currentName.add(currentNameTokenazer.nextToken());
        }
        for (int i = 0; i < currentName.size(); i++) {
            if (currentName.get(i).length() >= width - 1) {

                // TODO implement it

            } else if (currentName.get(i).length() <= width / 2) {
                // TODO implement it
            } else if ((currentName.get(i).length() > width) && (currentName.get(i).length() < width - 2)) {
                //TODO DO it Bl'ya
            }

        }


        return stringCounter;
    }

    public void format(InputStream inputStream, OutputStream outputStream) throws IOException {
        String currentLine;
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        currentLine = reader.readLine();
        while (currentLine != null) {
            StringTokenizer currentStringTokenazer = new StringTokenizer(currentLine, defaultDelimiter, false);
            ArrayList<String> list = new ArrayList<String>();
            while (currentStringTokenazer.hasMoreTokens()) {
                list.add(currentStringTokenazer.nextToken());
            }

            // TODO createStrings
            builder.append(headRawWriter(config.getColumnConfigs()));
            builder.append("\r\n");
            builder.append(stringDelimiter());
            builder.append("\r\n");
            builder.append(rowWriter(config.getColumnConfigs(), list));
            builder.append(stringDelimiter());
            builder.append("\r\n");


        }


    }

}
