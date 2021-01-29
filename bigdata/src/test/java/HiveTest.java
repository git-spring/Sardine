import com.star.common.baseApi.HiveAPI;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Spring
 */
public class HiveTest {

    @Test
    public void queryDataTest() throws SQLException {
        String sql = "select * from test0315.video_user";
        ResultSet rs = HiveAPI.queryData(sql);
        System.out.println(rs);
    }

    @Test
    public void foreachResultSetTest() throws SQLException {
        String sql = "select * from test0315.video_user limit 10";
        HiveAPI.foreachResultSet(sql);

    }
}