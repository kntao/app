package onlinesimulate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qingtao.kong on 2015/11/5.
 */
public class NewsClick {
    private static final Logger logger = LoggerFactory.getLogger("app.news.click");

    public static void main(String[] args) {
        String uid = "768";
        String infoType = "1";
        String rowKey = "481c7ffffeaf2a991f4346d9e4910000";
        String channel = "21";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        for(int i = 0; i < 2; i++) {
            String message = String.format("%s,%s,%s,%s,%s", uid, infoType, rowKey, channel, date);
            logger.info(message);
        }
    }
}
