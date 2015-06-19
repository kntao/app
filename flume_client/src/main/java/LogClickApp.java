import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qingtao.kong on 2015/3/9.
 */
public class LogClickApp {
    private static final Logger logger = LoggerFactory.getLogger("app.click");
    public static void main(String [] args){
        //Log4jXMLConfig.init();

        while (true) {
            String uid = "XXXXXXXXXXXXXX";
            String rowkey = "481c7ffffeb2d93aaf60593efa250000";
            String infotype = "1";
            String message = String.format("%s,%s,%s", uid, infotype, rowkey);
            logger.info(message);
        }
    }
}
