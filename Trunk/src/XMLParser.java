/**
 * User: Alex
 * Date: 17.03.14
 * Time: 21:42
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;

public class XMLParser {
    public HashMap<String,Currency> GetCurrencyListFromXML(String stringXML){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        HashMap<String,Currency> hmCurrency = new HashMap<>();
        try{
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(IOUtils.toInputStream(stringXML, "UTF-8"));
            document.getDocumentElement().normalize();
            NodeList nodes = document.getElementsByTagName("ValuteCursOnDate");

            for(int i=0; i<nodes.getLength(); i++){
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    hmCurrency.put(element.getElementsByTagName("VchCode").item(0).getTextContent(),
                                   new Currency(
                                        element.getElementsByTagName("Vname").item(0).getTextContent(),
                                        element.getElementsByTagName("Vnom").item(0).getTextContent(),
                                        element.getElementsByTagName("Vcurs").item(0).getTextContent(),
                                        element.getElementsByTagName("Vcode").item(0).getTextContent(),
                                        element.getElementsByTagName("VchCode").item(0).getTextContent()
                                   )
                    );

                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return hmCurrency;
    }
}
