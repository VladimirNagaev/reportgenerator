import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 16.06.2020.
 */
public class ConfigParser {


    public ReportConfig parseFromAddress(String address) throws ParserConfigurationException, IOException, SAXException {

        ReportConfig reportConfig = new ReportConfig(); // to full it
        List<ColumnConfig> parsedCollumnConfiges = new ArrayList<ColumnConfig>();


        File configFile = new File(address);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(configFile);
        Element root = document.getDocumentElement();


        NodeList tableMain = root.getElementsByTagName("page");


        for (int i = 0; i < tableMain.item(0).getChildNodes().getLength(); i++) {
            if (tableMain.item(0).getChildNodes().item(i).getNodeName().equals("height")) {

                reportConfig.setHeight(Integer.valueOf(tableMain.item(0).getChildNodes().item(i).getTextContent()));
            } else if (tableMain.item(0).getChildNodes().item(i).getNodeName().equals("width")) {
                reportConfig.setWidth(Integer.valueOf(tableMain.item(0).getChildNodes().item(i).getTextContent()));

            }
        }


        NodeList collumsNodelist = document.getElementsByTagName("column");

        for (int i = 0; i < collumsNodelist.getLength(); i++) {
            if (collumsNodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {

                Element columnElemrnt = (Element) collumsNodelist.item(i);
                ColumnConfig columnConfig = new ColumnConfig();



                for (int j = 0; j < columnElemrnt.getChildNodes().getLength(); j++) {


                    if (columnElemrnt.getChildNodes().item(j).getNodeName().equals("title")) {
                        columnConfig.setTitle(columnElemrnt.getChildNodes().item(j).getTextContent());
                    } else if (columnElemrnt.getChildNodes().item(j).getNodeName().equals("width")) {
                        columnConfig.setWidth(Integer.valueOf(columnElemrnt.getChildNodes().item(j).getTextContent()));
                    }


                }
                parsedCollumnConfiges.add(columnConfig);

            }

        }

        reportConfig.setColumnConfigs(parsedCollumnConfiges);

        return reportConfig;
    }


}
