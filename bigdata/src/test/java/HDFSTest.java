import com.star.hadoop.HDFSRemote;

import org.junit.Test;

/**
 * @author: liudw
 * @date: 2021-4-26 14:16
 */

public class HDFSTest {

    HDFSRemote hdfs = new HDFSRemote();

    @Test
    public void test() {
        hdfs.ifExists("/user/hive/warehouse/assetcenter.db/zczm_zzc_trans_v2/busi_date=20210419");
    }

    @Test
    public void lsTest() {
        hdfs.lsFiles("/user/hive/warehouse/assetcenter.db/zczm_zzc_trans_v2/busi_date=20210419");
    }
}
