import com.hbase.HbaseUtils;
import org.junit.Test;

/**
 * @author Spring
 */
public class HbaseTest {

    @Test
    public void getCellTest(){
        String cell = HbaseUtils.getCell("bill:test", "00008", "f", "c");
        System.out.println(cell);
    }
}