/**
 * User: Alex
 * Date: 16.03.14
 * Time: 23:51
 */
public class Currency {
    private String Vname; //Название валюты
    private String Vnom; // Номинал
    private String Vcurs; // Курс
    private String Vcode; // Цифровой код валюты
    private String VchCode; // Символьный код валюты

    public Currency(String Vname, String Vnom, String Vcurs, String Vcode, String VchCode){
        this.Vname = Vname;
        this.Vnom = Vnom;
        this.Vcurs = Vcurs;
        this.Vcode = Vcode;
        this.VchCode = VchCode;
    }

    public String getVname(){
        return Vname;
    }

    public String getVnom(){
        return Vnom;
    }

    public String getVcurs(){
        return Vcurs;
    }

    public String getVcode(){
        return Vcode;
    }

    public String getVchCode(){
        return VchCode;
    }
}
