/**
 * User: Alex
 * Date: 16.03.14
 * Time: 23:36
 */
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProjectProperties {
    private static ProjectProperties instance;
    private String SoapMSG;
    private String CB_URL;
    private String SOAPAction;
    private String Content_Type;

    private ProjectProperties(){
        try (InputStreamReader inputPropertiesFile = new InputStreamReader(new FileInputStream("DefaultSettings.properties"), "UTF-8");
             FileInputStream inputSOAPMsg = new FileInputStream("SOAPmsg")) {

            Properties propertiesFromFile = new Properties();
            propertiesFromFile.load(inputPropertiesFile);
            CB_URL = new String(propertiesFromFile.getProperty("CB_URL"));
            SOAPAction = new String(propertiesFromFile.getProperty("SOAPAction"));
            Content_Type = new String(propertiesFromFile.getProperty("Content_Type"));

            SoapMSG = new String(IOUtils.toString(inputSOAPMsg));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ProjectProperties getInstance(){
        if (instance == null) instance = new ProjectProperties();
        return instance;
    }

    public String getSoapMSG(){
        //replace делаем в гетере, а не в конструкторе, потому что возможно что запустим программу сегодня, а обновим завтра
        return SoapMSG.replace("NOW", new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }

    public String getCB_URL(){
        return CB_URL;
    }

    public String getSOAPAction(){
        return SOAPAction;
    }

    public String getContent_Type(){
        return Content_Type;
    }
}

