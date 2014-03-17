/**
 * User: Alex
 * Date: 09.03.14
 * Time: 20:30
 */
import java.io.*;

public class Main {

   public static void main(String[] args){
       CurrencyRate currencyRate = CurrencyRate.getInstance();
       try{
            currencyRate.UpdateCurrencyRate();
       }catch (IOException ex)
       {
        ex.printStackTrace();
       }
   }
}
