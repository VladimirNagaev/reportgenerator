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

    public String rowWriter(List<ColumnConfig> columnConfigs, List<String> thisRow, int rowCounter) {
        StringBuilder thisString = new StringBuilder();
        StringTokenizer st = new StringTokenizer(thisRow.get(2), "*", false);
        List<String> nameData = new ArrayList<String>();
        while (st.hasMoreTokens()) {
            nameData.add(st.nextToken());
        }
        int willBeRows = howManyRowDefiner(thisRow.get(2));

        if (rowCounter < config.getHeight() - willBeRows) {
            rowCounter += willBeRows;

        } else {
            thisString.append(stringDelimiter());
            thisString.append("\r\n");
            thisString.append("`");
            thisString.append("\r\n");
            rowCounter = 0;
        }

        for (int i = 0; i < howManyRowDefiner(thisRow.get(thisRow.size() - 1)); i++) {
            for (int j = 0; j < columnConfigs.size(); j++) {
                if (thisRow.get(1).length() < columnConfigs.get(1).getWidth()) {
                    if (j != 2) {
                        int counter = 0;
                        thisString.append("|");
                        thisString.append(" ");
                        counter += 2;
                        thisString.append(thisRow.get(j));
                        counter += thisRow.get(j).length();
                        while (counter < columnConfigs.get(j).getWidth() - 1) {
                            thisString.append(" ");
                            counter++;
                        }
                        thisString.append("|");

                    } else {
                        int counter = 0;
                        thisString.append("|");
                        thisString.append(" ");
                        counter += 2;
                        thisString.append(nameData.get(i));


                    }
                } else {
                    if (j != 2) {
                        int counter = 0;
                        thisString.append("|");
                        thisString.append(" ");
                        counter += 2;
                        if (j == 2) {
                            thisString.append(thisRow.get(j));//  2 строки дату

                        }
                        counter += thisRow.get(j).length();
                        while (counter < columnConfigs.get(j).getWidth() - 1) {
                            thisString.append(" ");
                            counter++;
                        }
                        thisString.append("|");

                    } else {
                        int counter = 0;
                        thisString.append("|");
                        thisString.append(" ");
                        counter += 2;
                        thisString.append(nameData.get(i));


                    }


                }

            }
            thisString.append("\r\n");

        }


        return thisString.toString();
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

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        currentLine = reader.readLine();
        builder.append(headRawWriter(config.getColumnConfigs()));// шапка
        builder.append("\r\n");
        rowCounter++;
        builder.append(stringDelimiter()); // разделитель
        builder.append("\r\n");
        rowCounter++;

        while (currentLine != null) {
            StringTokenizer currentStringTokenazer = new StringTokenizer(currentLine, defaultDelimiter, false);
            ArrayList<String> list = new ArrayList<String>();

            while (currentStringTokenazer.hasMoreTokens()) {
                list.add(currentStringTokenazer.nextToken());
            }

            // TODO createStrings

            builder.append(rowWriter(config.getColumnConfigs(), list, rowCounter)); // сама строка
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

            } else {
                continue;
            }



        }



    }

}
