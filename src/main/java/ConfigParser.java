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


    public  ReportConfig parseFromAddress(String address) throws ParserConfigurationException, IOException, SAXException {

        ReportConfig reportConfig = new ReportConfig(); // to full it
        List<ColumnConfig> parsedCollumnConfiges = new ArrayList<ColumnConfig>();

             // TODO   implement later
        File configFile = new File(address);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(configFile);

        Element tableMain = (Element) document.getElementsByTagName("page");
        reportConfig.setHeight(Integer.valueOf(tableMain.getAttribute("height")));
        reportConfig.setWidth(Integer.valueOf(tableMain.getAttribute("width")));

        NodeList collumsNodelist = document.getElementsByTagName("column");

        for (int i = 0; i < collumsNodelist.getLength() ; i++) {
            if( collumsNodelist.item(i).getNodeType() == Node.ELEMENT_NODE){

                Element columnElemrnt = (Element) collumsNodelist.item(i);
                ColumnConfig columnConfig = new ColumnConfig();
                columnConfig.setTitle(columnElemrnt.getAttribute("title"));
                columnConfig.setWidth(Integer.valueOf(columnElemrnt.getAttribute("width")));

                parsedCollumnConfiges.add(columnConfig);

            }
            
        }

        reportConfig.setColumnConfigs(parsedCollumnConfiges);

        return reportConfig;
    }




}
