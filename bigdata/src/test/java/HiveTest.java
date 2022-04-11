import com.star.common.baseApi.HiveAPI;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Spring
 */
public class HiveTest {

    @Test
    public void queryDataTest1() throws SQLException {
        String sql = "select * from dm_dhyw.dm_yw_achievement_day limit 1";
        ResultSet rs = HiveAPI.queryData(sql);
        while(rs.next()){
            // 下面的写法 在工作中 经常这么写
            String comcode=rs.getString("comcode");
            String organization=rs.getString("organization");
            String typea_day=rs.getString("typea_day");
            System.out.println("col1："+comcode+",col2："+organization+",col3："+typea_day);
            System.out.println("-----------------------");
        }

    }

    @Test
    public void queryDataTest() throws SQLException {
        String sql = "select * from test0315.video_user limit 10";
        ResultSet rs = HiveAPI.queryData(sql);
        while(rs.next()){
            // 下面的写法 在工作中 经常这么写
            String uploader=rs.getString("uploader");
            int videos=rs.getInt("videos");
            int friends=rs.getInt("friends");
            System.out.println("up猪："+uploader+",视频数量："+videos+",关注数量："+friends);
            System.out.println("-----------------------");
        }

    }

    @Test
    public void foreachResultSetTest() throws SQLException {
        String sql = "select * from test0315.video_user limit 10";
        HiveAPI.foreachResultSet(sql);

    }
}