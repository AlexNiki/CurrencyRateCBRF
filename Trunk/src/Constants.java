/**
 * User: a.arzamastsev Date: 18.03.14 Time: 7:52
 */
public interface Constants {
    public static final String UTF_8                = "UTF-8";
    public static final String DATE_PATTERN         = "yyyy-MM-dd";
    public static final String DESTINATION_CBR      = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
    public static final String CBR_URL              = "http://web.cbr.ru/";
    public static final String NO_PREFIX            = "";
    public static final String GET_CURS_ON_DATE_XML = "GetCursOnDateXML";
    public static final String ON_DATE              = "On_date";
    public static final String NODE_CURS_ON_DATE    = "ValuteCursOnDate";
    public static final String CUR_CH_CODE          = "VchCode";
    public static final String CUR_NAME             = "Vname";
    public static final String CUR_NOM              = "Vnom";
    public static final String CUR_CURS             = "Vcurs";
    public static final String CUR_CODE             = "Vcode";


    public enum ConversionRateError {
        ERROR_FORMING_SOAP(0, "Ошибка формирования SOAP запроса."),
        INCORRECT_RESPONSE(1, "Некорректный ответ сервера ЦБ РФ."),
        ERROR_CONNECT(2,"Не удалось обратиться к серверу ЦБ РФ.");

        private final int code;
        private final String description;

        private ConversionRateError(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code + ": " + description;
        }
    }
}
