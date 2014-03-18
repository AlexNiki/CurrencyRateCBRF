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

    private CurrencyRate (){}

    public static CurrencyRate getInstance()
    {
        if (instance == null)
            instance = new CurrencyRate();

        return instance;
    }

    public void updateCurrencyRate() throws Exception{
        BankClient bankClient = BankClient.getInstance();
        XMLParser xmlParser = new XMLParser();
        try{
            hmCurrencyRate = xmlParser.GetCurrencyListFromXML(bankClient.getCursOnDateXml());
        }catch (IOException ex){
            throw new Exception("Не удалось обратиться к серверу ЦБ РФ");
        }
    }

    public String getCurName(String VchCode){
        return hmCurrencyRate.get(VchCode).getVname();
    }

    public String getCurRate(String VchCode){
        return hmCurrencyRate.get(VchCode).getVcurs();
    }

    public String getCurNom(String VchCode){
        return hmCurrencyRate.get(VchCode).getVnom();
    }

    public String getCurCode(String VchCode){
        return hmCurrencyRate.get(VchCode).getVcode();
    }
}
