/**
 * User: Alex
 * Date: 16.03.14
 * Time: 21:12
 */
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.io.IOUtils;
import java.io.*;

public class BankClient {
    private static BankClient instance;
    private ProjectProperties projectProperties;
    private HttpClient client;
    private HttpPost httpPost;

    private BankClient (){
        projectProperties = ProjectProperties.getInstance();
        client = HttpClientBuilder.create().build();
        httpPost = new HttpPost(projectProperties.getCB_URL());
        httpPost.addHeader("SOAPAction", projectProperties.getSOAPAction());
        httpPost.setHeader("Content-Type",projectProperties.getContent_Type());
    };

    public static BankClient getInstance()
    {
        if (instance == null) {
            instance = new BankClient();
        }
        return instance;
    }

    public String getValuteCursOnDateXml() throws IOException{
        String CursOnDateXml = new String();
        try {
                httpPost.setEntity(new StringEntity(projectProperties.getSoapMSG()));
                HttpResponse httpResponse = client.execute(httpPost);
                CursOnDateXml = IOUtils.toString((new InputStreamReader(httpResponse.getEntity().getContent())));
        } catch (Exception ex) {
            throw new IOException("Не удалось обратиться к серверу ЦБ РФ");
        }
        return CursOnDateXml;
    }
}
