import com.common.baseApi.Hbase_v1x;

import org.junit.Test;

/**
 * @author Spring
 */
public class HbaseTest {

    @Test
    public void getCellTest(){
        String cell = Hbase_v1x.getCell("bill:test", "00008", "f", "c");
        System.out.println(cell);
    }
}