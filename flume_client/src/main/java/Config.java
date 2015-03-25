import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by qingtao.kong on 2015/3/10.
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static byte[] lock = new byte[0];
    private static Config config;
    private static Properties prop;

    private Config(){}

    public static Config getInstance(){
        if(null == config){
            synchronized (lock){
                if(null == config){
                    config = new Config();
                    prop = new Properties();
                    try{
                        InputStream in = Config.class.getResourceAsStream("/client.properties");
                        prop.load(in);
                    }
                    catch (IOException e){
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        return  config;
    }

    public String getValue(String key){
        return prop.getProperty(key);
    }
}
