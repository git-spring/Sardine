import com.star.common.baseApi.Hbase_v2x;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellScanner;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Spring
 */
public class HbaseTest {

    Hbase_v2x hbase = new Hbase_v2x();

    // 创建命名空间
    @Test
    public void createNameSpace() throws IOException {
        hbase.createNameSpace("test");
    }


    // hbase 表是否存在
    @Test
    public void isTableExists() throws IOException {
        boolean tableExists = hbase.isTableExists("bigdata", "test1019");
        System.out.println(tableExists);
    }

    @Test
    public void createTable() throws IOException {
        hbase.createTable("bigdata","test1020", "f1","f2");
    }


    @Test
    public void putRow() {
        hbase.putRow("bigdata", "test1021","00001","f1","info","hahah");
    }

    @Test
    public void getValueByRowkey() {
        Result result = hbase.getValueByRowkey("bigdata", "test1021", "00001");
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            String value = new String(CellUtil.cloneValue(cell));
            System.out.println(value);
        }
    }

    @Test
    public void scanTable() throws IOException {
        hbase.createTable("t2", "f");
    }
}