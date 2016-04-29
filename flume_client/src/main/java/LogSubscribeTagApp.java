import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qingtao.kong on 2015/3/9.
 */
public class LogSubscribeTagApp {
    private static final Logger logger = LoggerFactory.getLogger("app.tags.subscribe");

    public static void main(String [] args) throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = 0;
        while (true) {
            String uid = "x" + i++;
            int type = 0;
            String tag = "JUNIOR_82000|JUNIOR_820005";
            String cancelTag = "";
            String da = df.format(new Date());
            String message = String.format("%s,%s,%s,%s,%s", uid, type, tag, cancelTag, da);
            System.out.println(message);
            logger.info(message);
            Thread.sleep(5000);
        }
    }
}
