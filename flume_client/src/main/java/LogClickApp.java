import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qingtao.kong on 2015/3/9.
 */
public class LogClickApp {
    private static final Logger logger = LoggerFactory.getLogger("app.news.click");
    public static void main(String [] args) throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int i = 0;
        while (true) {
            String uid = "y" + i++;
            String infotype = "1";
            String rowkey = "481c7ffffeaf2a991f4346d9e4910000";
            String channel = "21";
            String date = df.format(new Date());
            String message = String.format("%s,%s,%s,%s,%s", uid, infotype, rowkey,channel,date);
            message = "1559,1,481c7ffffead6041dcb4f69de6010000,21,2016-02-02 13:04:08";

            logger.info(message);
            Thread.sleep(5000);
        }
    }
}
