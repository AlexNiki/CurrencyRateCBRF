/**
 * User: a.arzamastsev Date: 19.03.14 Time: 9:09
 */
import static org.junit.Assert.*;
public class CurrencyRateTest {
    @org.junit.Test
    public void testCurrencyRate() throws Exception {
        CurrencyRate currencyRate = CurrencyRate.getInstance();
        currencyRate.updateCurrencyRate();
        System.out.print(currencyRate.getCurCode("USD"));
        assertNotNull(currencyRate.getCurCode("USD"));
    }
}


//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class TestHello {
//
//    @Test
//    public void testPasses() {
//        String expected = "Hello, JUnit!";
//        String hello = "Hello, JUnit!";
//        assertEquals(hello, expected);
//    }
//
//    @Test
//    public void testFails() {
//        // The worlds most obvious bug:
//        assertTrue(false);
//    }
//
//    @Test
//    public void testArray() {
//        int [] array1 = new int[] {1, 2, 3};
//        int [] array2 = new int[] {1, 2, 3};
//        assertArrayEquals(array1, array2);
//    }
//}