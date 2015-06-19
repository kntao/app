import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.spark.storage.StorageLevel;

import java.util.Map;

/**
 * Created by qingtao.kong on 2015/3/13.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String jsonStr = args[0];
        Map<String,Float> map_tags = JSON.parseObject(jsonStr, new TypeReference<Map<String, Float>>() {
        });
        System.out.println(map_tags.size());
        System.out.println(map_tags.size());

    }
}
