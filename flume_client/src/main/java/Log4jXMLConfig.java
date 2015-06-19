import org.apache.log4j.xml.DOMConfigurator;

import java.net.URL;

/**
 * Created by qingtao.kong on 2015/5/11.
 */
public class Log4jXMLConfig {
    private static Log4jXMLConfig instance;

    public static synchronized Log4jXMLConfig init()
    {
        if (instance == null)
        {
            return new Log4jXMLConfig();
        }
        return instance;
    }

    private Log4jXMLConfig()
    {
        final URL url = Log4jXMLConfig.class.getResource("log4j.xml.tmp");
        DOMConfigurator.configure(url);

    }
}
