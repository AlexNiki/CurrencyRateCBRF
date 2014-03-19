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

    public int getErrorCode(){
        return this.error.getCode();
    }
}
