import javax.xml.soap.SOAPException;
import javax.xml.transform.TransformerException;

/**
 * User: Alex
 * Date: 19.03.14
 * Time: 14:48
 */
public class ConversionRateException extends Exception implements Constants {
    private ConversionRateError error;

    public ConversionRateException(ConversionRateError error){
        super(error.getDescription());
        this.error = error;
    }

    public ConversionRateException(ConversionRateError error, Throwable ex){
        super(error.getDescription());
        this.error = error;
        this.initCause(ex);
    }

    public ConversionRateException(Throwable ex){
        if (ex instanceof SOAPException){
            this.error = ConversionRateError.ERROR_FORMING_SOAP;
        } else if (ex instanceof TransformerException) {
            this.error = ConversionRateError.INCORRECT_RESPONSE;
        } else {
            this.error = ConversionRateError.ERROR_CONNECT;
        }
        this.initCause(ex);
    }

    public int getErrorCode(){
        return this.error.getCode();
    }
}
