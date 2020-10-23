import com.common.baseApi.HbaseAPI;

import org.junit.Test;

/**
 * @author Spring
 */
public class HbaseTest {

    @Test
    public void getCellTest(){
        String cell = HbaseAPI.getCell("bill:test", "00008", "f", "c");
        System.out.println(cell);
    }
}