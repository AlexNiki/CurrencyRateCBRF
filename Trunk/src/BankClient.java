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

    public String getCursOnDateXml() throws ConversionRateException{
        String CursOnDateXml;
        try{
            SOAPConnection soapConnection = SOAPConnectionFactory.newInstance().createConnection();
            String destination = DESTINATION_CBR;
            SOAPMessage soapMessage = soapConnection.call(getSoapRequestToCursOnDate(), destination);

            StringWriter stringWriter = new StringWriter();
            TransformerFactory.newInstance().newTransformer().transform(soapMessage.getSOAPPart().getContent(),
                                                                        new StreamResult(stringWriter));
            CursOnDateXml = stringWriter.toString();
            soapConnection.close();
        } catch (SOAPException ex) {
            ConversionRateException crException = new ConversionRateException(ConversionRateError.ERROR_FORMING_SOAP);
            crException.initCause(ex);
            throw crException;
        } catch (TransformerException ex) {
            ConversionRateException crException = new ConversionRateException(ConversionRateError.INCORRECT_RESPONSE);
            crException.initCause(ex);
            throw crException;
        } catch (Exception ex) {
            ConversionRateException crException = new ConversionRateException(ConversionRateError.ERROR_CONNECT);
            crException.initCause(ex);
            throw crException;
        }
        return CursOnDateXml;
    }

    private SOAPMessage getSoapRequestToCursOnDate() throws SOAPException{
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message   = messageFactory.createMessage();
        SOAPPart soapPart     = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body         = envelope.getBody();

        SOAPElement bodyElement = body.addChildElement(envelope.createName(GET_CURS_ON_DATE_XML, NO_PREFIX, CBR_URL));
        bodyElement.addChildElement(ON_DATE).addTextNode(new SimpleDateFormat(DATE_PATTERN).format(Calendar.getInstance().getTime()));
        message.saveChanges();
        return message;
    }
}
