/**
 * User: Alex
 * Date: 09.03.14
 * Time: 20:31
 */
import java.util.*;
import java.io.*;

public class CurrencyRate {
    private static CurrencyRate instance;
    private HashMap<String,Currency> hmCurrencyRate = new HashMap<>();

    private CurrencyRate (){};

    public static CurrencyRate getInstance()
    {
        if (instance == null) {
            instance = new CurrencyRate();
        }
        return instance;
    }

    public void UpdateCurrencyRate() throws IOException{
        BankClient bankClient = BankClient.getInstance();
        XMLParser xmlParser = new XMLParser();
        try{
            hmCurrencyRate = xmlParser.GetCurrencyListFromXML(new String(bankClient.getValuteCursOnDateXml()));
        }catch (IOException ex){
            throw new IOException("Не удалось обратиться к серверу ЦБ РФ");
        }
    }

    public String GetCurName(String VchCode){
        return hmCurrencyRate.get(VchCode).getVname();
    }

    public String GetCurRate(String VchCode){
        return hmCurrencyRate.get(VchCode).getVcurs();
    }

    public String GetCurNom(String VchCode){
        return hmCurrencyRate.get(VchCode).getVnom();
    }

    public String GetCurCode(String VchCode){
        return hmCurrencyRate.get(VchCode).getVcode();
    }
}
