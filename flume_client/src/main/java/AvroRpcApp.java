import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by qingtao.kong on 2015/3/10.
 */
public class AvroRpcApp {

    public static void main(String args[]) throws Exception{

        AvroRpcClientFacade client = new AvroRpcClientFacade();
        client.init(Config.getInstance().getValue("agent.host"),Integer.parseInt(Config.getInstance().getValue("agent.port")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        for(int i = 0; i < 1000000000; i++){
            System.out.println(i + ": " + sdf.format(new Date()));
            client.sendDataToFlume(i + ": " + sdf.format(new Date()));
            Thread.sleep(500);
        }
        client.cleanUp();
    }



}