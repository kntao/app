import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qingtao.kong on 2015/3/5.
 */
public class LogTestApp {
    private static Logger logger = LoggerFactory.getLogger("app.test");


    public static void main(String args[]) throws Exception {
        int threadCount = 10;
        for(int j = 0; j < threadCount; j++) {
            new Thread("t" + j){
              public  void run(){
                  for (int i = 0; i < 100000000; i++) {
                      logger.info("uid" + i + "login!");
                      logger.debug("login come from " + i);
                  }
              }
            }.start();
        }
    }

}