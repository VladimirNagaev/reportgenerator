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
    List<List<String>> allOurData;


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

            while (cs.length() < columnConfigs.get(i).getWidth()) {
                cs.append(" ");
            }

            /*
            for (int j = cs.length(); j < columnConfigs.get(i).getWidth(); j++) {
                cs.append(" ");
            }
            */
            cs.append("| ");
            headerString.append(cs);
        }


        return headerString.toString();
    }

    public int howManyRowDefiner(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        int rows = 1;
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '*') {
                rows++;
            }
        }
        return rows;
    }

    public List<String> stringForPartsDevider(String string, int width) {
        List<String> stringList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder(string);
        int rest = stringBuilder.length();
        int now = 0;
        if (string.length() > width-1) {
            while (rest >= width) {
                stringList.add(stringBuilder.substring(now, now + width ));
                now += width;
                rest -= width;
            }

            return stringList;
        } else {
            return null;
        }

    }

    public String rowWriter(List<String> ourRow, int rowCounter) {
        StringBuilder result = new StringBuilder();

        int willBeRows = 1;

        for (int i = 0; i < ourRow.size(); i++) {
            if (stringForPartsDevider(ourRow.get(i), config.getColumnConfigs().get(i).getWidth()) != null) {
                if (willBeRows < stringForPartsDevider(ourRow.get(i), config.getColumnConfigs().get(i).getWidth()).size()) {
                    willBeRows = stringForPartsDevider(ourRow.get(i), config.getColumnConfigs().get(i).getWidth()).size();
                }
            }
        }

        int crSize = 0;
        int rowNumber = 1;

        for (int i = 0; i < willBeRows; i++) { // по строкам
            boolean alreadyWrittenOnce = false;
            if (i > 0) {
                alreadyWrittenOnce = true;
            }
            for (int j = 0; j < ourRow.size(); j++) { // по колонкам
                if (stringForPartsDevider(ourRow.get(j), config.getColumnConfigs().get(j).getWidth()) == null) {
                    crSize = result.length();
                    result.append("| ");

                    if (!alreadyWrittenOnce) {
                        result.append(ourRow.get(j));
                    }


                    while (result.length() < crSize + config.getColumnConfigs().get(j).getWidth() + 2) {
                        result.append(" ");

                    }


                } else {
                    crSize = result.length();

                    // а вот тут будет обрабатываться то что с боьшими именами и датами
                    if (result.length() > 1 && result.charAt(result.length() - 1) != '|') {
                        result.append("| ");
                    } else {
                        result.append("  ");
                    }
                    // result.append("| ");
                    if (stringForPartsDevider(ourRow.get(j), config.getColumnConfigs().get(j).getWidth()).size() > i) {
                        result.append(stringForPartsDevider(ourRow.get(j), config.getColumnConfigs().get(j).getWidth()).get(i));
                        while (result.length() < crSize + config.getColumnConfigs().get(j).getWidth() ) {
                            result.append(" ");

                        }
                        result.append(" |");

                    } else {

                        while (result.length() < crSize + config.getColumnConfigs().get(j).getWidth()+1) {
                            result.append(" ");

                        }
                        result.append(" |");


                    }

                }

            }
            result.append("\r\n");
            rowCounter++;


        }

        return result.toString();


    }

    public void setDefaultDelimiter(String defaultDelimiter) {
        this.defaultDelimiter = defaultDelimiter;
    }

    public String stringDivider(String string, int width) { // divides names for parts to write in into stream
        // this void will chsnge inputed string to " Иван * ов Ки * рилл" where * is row delimiter
        StringBuilder outputString = new StringBuilder(string);
        List<String> currentName = new ArrayList<String>();

        int now = width - 1;
        int rest = outputString.length() - 1;
        while (rest > width) {
            outputString.insert(now, " * ");
            now += width - 1;
            rest -= (width + 1);
        }


        return outputString.toString();
    }

    public void format(InputStream inputStream, OutputStream outputStream) throws IOException {
        int rowCounter = 0;
        String currentLine;
        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-16"));
        currentLine = reader.readLine();
        builder.append(headRawWriter(config.getColumnConfigs()));// шапка
        builder.append("\r\n");
        rowCounter++;
        builder.append(stringDelimiter()); // разделитель
        builder.append("\r\n");
        rowCounter++;

        while (currentLine != null) {
            if (currentLine.length() == 1 && currentLine.charAt(0) == '\0') {
                currentLine = reader.readLine();
                continue;
            }
            StringTokenizer currentStringTokenazer = new StringTokenizer(currentLine, defaultDelimiter, false);
            ArrayList<String> list = new ArrayList<String>();


            while (currentStringTokenazer.hasMoreTokens()) {
                list.add(currentStringTokenazer.nextToken());
            }


            builder.append(rowWriter(list, rowCounter)); // сама строка
            builder.append(stringDelimiter());
            builder.append("\r\n");
            rowCounter++;
            if (rowCounter >= config.getHeight() - 2) {
                builder.append("\r\n");
                builder.append("`");
                builder.append("\r\n");
                rowCounter = 0;
                builder.append(headRawWriter(config.getColumnConfigs()));// шапка
                builder.append("\r\n");
                rowCounter++;
                builder.append(stringDelimiter()); // разделитель
                builder.append("\r\n");
                rowCounter++;

            }
            currentLine = reader.readLine();


        }
        outputStream.write(builder.toString().getBytes());


        outputStream.write(builder.toString().getBytes());

    }

}
