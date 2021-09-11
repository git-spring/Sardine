import com.star.common.baseApi.Hbase_v2x;

import org.junit.Test;

/**
 * @author Spring
 */
public class HbaseTest {

    Hbase_v2x hbase = new Hbase_v2x();

    @Test
    public void getValueByRowkey() {

        hbase.getAllCellByRowkey("stockdealrecords", "03 1418903 2021");
    }

    @Test
    public void scanTable() {
        hbase.createTable("t2", "f");
    }
}