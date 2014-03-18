/**
 * User: Alex
 * Date: 16.03.14
 * Time: 21:12
 */
import java.io.*;
import javax.xml.soap.*;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BankClient implements Constants{
    private static BankClient instance;

    private BankClient (){}

    public static BankClient getInstance()
    {
        if (instance == null) {
            instance = new BankClient();
        }
        return instance;
    }

    public String getCursOnDateXml() throws Exception{
        String CursOnDateXml = null;
        SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnFactory.createConnection();
        try {
            String destination = DESTINATION_CBR;
            SOAPMessage soapMessage = soapConnection.call(getSoapRequestToCursOnDate(), destination);

            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(soapMessage.getSOAPPart().getContent(),
                                                                        new StreamResult(stringWriter));
            CursOnDateXml = stringWriter.toString();
        } catch (SOAPException ex) {
            throw new Exception(ex.getCause());
        } catch (TransformerException e) {
            throw new Exception("Некорректный ответ сервера ЦБ РФ");
        } catch (Exception ex) {
            throw new Exception("Не удалось обратиться к серверу ЦБ РФ");
        } finally {
            soapConnection.close();
        }
        return CursOnDateXml;
    }

    private SOAPMessage getSoapRequestToCursOnDate() throws SOAPException{
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();
        try {
            SOAPPart soapPart     = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            SOAPBody body         = envelope.getBody();

            SOAPElement bodyElement = body.addChildElement(envelope.createName(GET_CURS_ON_DATE_XML, NO_PREFIX, CBR_URL));
            bodyElement.addChildElement(ON_DATE).addTextNode(new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime()));
            message.saveChanges();
        }catch (SOAPException e){
            throw new SOAPException("Ошибка формирования SOAP запроса");
        }
        return message;
    }

}