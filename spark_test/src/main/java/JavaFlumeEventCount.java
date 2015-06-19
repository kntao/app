import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.flume.FlumeUtils;
import org.apache.spark.streaming.flume.SparkFlumeEvent;

/**
 * Created by qingtao.kong on 2015/3/16.
 */
public class JavaFlumeEventCount {
    public static void main(String[] args){
        if(args.length != 2){
            System.err.print("Usage: JavaFlumeEventCount <host> <port>");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        JavaStreamingContext jssc = new JavaStreamingContext(new SparkConf().setAppName("JavaFlumeEventCount"), Durations.seconds(2));
        JavaReceiverInputDStream<SparkFlumeEvent> flumeStream = FlumeUtils.createPollingStream(jssc, host, port);

        flumeStream.count();

        flumeStream.count().map(new Function<Long, String>() {
            @Override
            public String call(Long aLong) throws Exception {
                return "Received " + aLong + " flume events.";
            }
        }).print();

        jssc.start();
        jssc.awaitTermination();
    }

}
