/**
 * User: Alex
 * Date: 16.03.14
 * Time: 23:36
 */

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class ProjectProperties implements Constants {
    private static ProjectProperties instance;
    private String soapMsg;
    private String url;
    private String soapAction;
    private String contentType;

    private ProjectProperties() {
        try (InputStreamReader inputPropertiesFile = new InputStreamReader(new FileInputStream("DefaultSettings.properties"), UTF_8);
            FileInputStream inputSOAPMsg = new FileInputStream("SOAPmsg")) {

            Properties propertiesFromFile = new Properties();
            propertiesFromFile.load(inputPropertiesFile);
            url = propertiesFromFile.getProperty(url);
            soapAction = propertiesFromFile.getProperty(SOAPACTION);
            contentType = propertiesFromFile.getProperty(CONTENT_TYPE);

            soapMsg = IOUtils.toString(inputSOAPMsg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ProjectProperties getInstance() {
        if (instance == null)
            instance = new ProjectProperties();
        return instance;
    }

    public String getSoapMsg() {
        //replace делаем в гетере, а не в конструкторе, потому что возможно что запустим программу сегодня, а обновим завтра
        return soapMsg.replace(NOW, new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime()));
    }

    public String getUrl() {
        return url;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public String getContentType() {
        return contentType;
    }
}