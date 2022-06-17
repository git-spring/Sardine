import com.star.common.baseApi.HiveAPI;
import com.star.common.baseApi.HiveAPI_v2;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Spring
 */
public class HiveTest {

    @Test
    public void getMetadataInfo() {
        String sql = "select * from dm_dhyw.dm_yw_achievement_day limit 10";
        HiveAPI.getMetadataInfo(sql);
    }

    @Test
    public void getMetadataInfo1() {
        HiveAPI_v2.getMetadataInfo("dm_dhyw","dm_yw_achievement_day");
    }
}