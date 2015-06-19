import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.SimpleFormatter;

/**
 * Created by qingtao.kong on 2015/3/10.
 */
public class AvroRpcApp {

    public static void main(String args[]) throws Exception{
        int threadCount = Integer.parseInt(args[0]);
        String[] user = {"uuu1","uuu2","uuuu3","uuuu4","uuuuu5","uuuuu6","uuuu7","uuuuu8","uuuu9","uuuu10","uuuu11","uuuuu12","uuuuu13"};
        String[] news = {"1a617ffffeb33ea485d9ea221c780000","1a617ffffeb33ea485ee86362c930000","481c7ffffeb33ea4861a4c16fcff0000",
        "481c7ffffeb33ea491b9ae894e3c0000","481c7ffffeb33ea491db4297acfa0000","481c7ffffeb33ea49e532874635d0000",
        "481c7ffffeb33ea4a3ab05ba98580000","1a617ffffeb33ea4a60e17af746b0000","481c7ffffeb33ea491cad4096a6a0000",
        "481c7ffffeb33ea49c53a77176090000","481c7ffffeb33ea49d5bf0321e640000","481c7ffffeb33ea4a075cd8a1dfd0000",
        "481c7ffffeb33ea4a2d6859217320000","1a617ffffeb33ea4a3fcdbc8e6020000","1a617ffffeb33ea4a2c1f8fcdf600000",
        "481c7ffffeb33ea4a39e68539b000000","481c7ffffeb33ea4a3bbb8367d780000","481c7ffffeb33ea4a7de6343456a0000",
        "1a617ffffeb33ea4a308303801e00000","481c7ffffeb33ea4a3e37e34a5ae0000","1a617ffffeb33ea4a7e8466e16ac0000",
        "481c7ffffeb33ea4b3528c7a4a570000","481c7ffffeb33ea4b35fab072d170000","1a617ffffeb33ea4b37018a30c730000",
        "1a617ffffeb33ea4b3994fc27cdb0000","1a617ffffeb33ea4b3afb817e24c0000","1a617ffffeb33ea4b3cbae219e5a0000",
        "481c7ffffeb33ea4b3df70abd1580000","481c7ffffeb33ea4ba8132fc09710000","481c7ffffeb33ea4babd3244df270000"};

        String fileName = args[1];
        String host = Config.getInstance().getValue("agent.host");
        int port = Integer.parseInt(Config.getInstance().getValue("agent.port"));

        final List<String> rowkeys = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String buf = null;
        while (null != ( buf= br.readLine())) {
            rowkeys.add(buf);
        }
        final String userPre = "XXXXXXXXXXXXXXXXXXXXXXXX";

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        final ConnectionPool pool = new ConnectionPool(100,10,host,port);
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String event_type = "app.click";
        final String info_type = "1";
        final String happened = "AvroRpcApp.java";

        for(int i = 0; i < threadCount; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run(){
                    Random r = new Random();
                    AvroRpcClientFacade client = pool.borrowConnection();
                    while (true){
                        Date date = new Date();
                        String log = String.format("%s,%s,%s,%s,%s,%s",formatter.format(date),event_type,userPre + r.nextInt(100000),info_type,rowkeys.get(r.nextInt(rowkeys.size())),happened);
                        System.out.println(log);
                        client.sendDataToFlume(log);
                    }
                }
            });
        }
    }
}