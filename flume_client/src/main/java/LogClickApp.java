import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qingtao.kong on 2015/3/9.
 */
public class LogClickApp {
    private static Logger logger = LoggerFactory.getLogger("app.click");
    public static void main(String [] args){
        for(int j = 0; j < 10; j++) {
            new Thread("t" + j){
                public  void run(){
                    for (int i = 0; i < 1000000000; i++) {
                        logger.info("uid" + i +",newsid" +i);
                        logger.debug("click come from " + i);
                    }
                }
            }.start();
        }
    }
}
