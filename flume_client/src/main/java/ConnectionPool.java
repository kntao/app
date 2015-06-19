import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by qingtao.kong on 2015/4/30.
 */
public class ConnectionPool {
    private BlockingQueue<AvroRpcClientFacade> pool;
    /** Maximum number of connections that the pool can have */
    private int maxPoolSize;
    /** Number of connections that should be created initially */
    private int initialPoolSize;
    /** Number of connections generated so far */
    private int currentPoolSize;
    private String host;
    private int port;


    public ConnectionPool(int maxPoolSize,int initialPoolSize,String host,int port){
        if( (initialPoolSize > maxPoolSize) || initialPoolSize<1 || maxPoolSize <1 ) {
            throw new IllegalArgumentException("Invalid pool size parameters");
        }
        // default max pool size to 10
        this.maxPoolSize = maxPoolSize>0 ? maxPoolSize : 10;
        this.initialPoolSize = initialPoolSize;
        this.host = host;
        this.port = port;
        this.pool = new LinkedBlockingQueue<AvroRpcClientFacade>(maxPoolSize);
        initPooledConnection();
    }

    private void initPooledConnection(){
        for(int i = 0; i < initialPoolSize; i++){
            openAndPoolConnection();
        }
    }

    private synchronized void openAndPoolConnection(){
        if(currentPoolSize == maxPoolSize) { return; }
        AvroRpcClientFacade client = new AvroRpcClientFacade();
        client.init(host,port);
        pool.offer(client);
        currentPoolSize++;
    }
    public AvroRpcClientFacade borrowConnection() {
        if(currentPoolSize < maxPoolSize) { openAndPoolConnection(); }
        AvroRpcClientFacade client = null;
        try {
            client =  pool.take();
        }
        catch (Exception e){

        }
        return  client;
    }
}
