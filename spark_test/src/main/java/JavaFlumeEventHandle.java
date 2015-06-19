import com.google.common.base.Optional;
import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.flume.FlumeUtils;
import org.apache.spark.streaming.flume.SparkFlumeEvent;
import scala.Tuple2;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;

/**
 * Created by qingtao.kong on 2015/3/16.
 */
public class JavaFlumeEventHandle {
    public static void main(String[] args){
        if(args.length != 4){
            System.err.print("Usage 2: JavaFlumeEventHandle <host> <port> <host> <port");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String host1 = args[2];
        int port1 = Integer.parseInt(args[3]);

        InetSocketAddress address1 = new InetSocketAddress(host,port);
        InetSocketAddress address2 = new InetSocketAddress(host1,port1);

        InetSocketAddress[] InetSocketAddressArray = {address1,address2};

      JavaStreamingContext jssc = new JavaStreamingContext(new SparkConf().setAppName("JavaFlumeEventHandle_1"), Durations.seconds(2));

//       JavaReceiverInputDStream<SparkFlumeEvent> flumeStream = FlumeUtils.createPollingStream(jssc, InetSocketAddressArray, StorageLevel.MEMORY_AND_DISK_SER_2());

        JavaReceiverInputDStream<SparkFlumeEvent> flumeStream = FlumeUtils.createPollingStream(jssc,host,port);
        JavaReceiverInputDStream<SparkFlumeEvent> flumeStream1 = FlumeUtils.createPollingStream(jssc, host1, port1);
        JavaDStream<SparkFlumeEvent> union = flumeStream.union(flumeStream1);


        JavaDStream<String> dStream = flumeStream.map(new Function<SparkFlumeEvent, String>() {
            @Override
            public String call(SparkFlumeEvent sparkFlumeEvent) throws Exception {
                return "event:" + new String(sparkFlumeEvent.event().getBody().array());
            }
        });
        dStream.foreachRDD(new Function2<JavaRDD<String>, Time, Void>() {
            Random r = new Random();
            @Override
            public Void call(JavaRDD<String> stringJavaRDD, Time time) throws Exception {
                if(!stringJavaRDD.partitions().isEmpty())
                    stringJavaRDD.saveAsTextFile("/test/spark/" + "p" +r.nextInt());
                return null;
            }
        });
//        JavaDStream<Long> javaDStream = flumeStream.count();
//        javaDStream.foreachRDD(new Function2<JavaRDD<Long>, Time, Void>() {
//            @Override
//            public Void call(JavaRDD<Long> longJavaRDD, Time time) throws Exception {
//                String counts = "Counts at time " + time + " " + longJavaRDD.collect();
//                System.out.println(counts);
//                return  null;
//            }
//        });

//        jssc.checkpoint(".");
//        JavaPairDStream<String,Long> acc =  flumeStream.count().mapToPair(new PairFunction<Long, String, Long>() {
//            @Override
//            public Tuple2<String, Long> call(Long aLong) throws Exception {
//                return new Tuple2<String, Long>("a",aLong);
//            }
//        });
//        acc.updateStateByKey(new Function2<List<Long>, Optional<Long>, Optional<Long>>() {
//            @Override
//            public Optional<Long> call(List<Long> longs, Optional<Long> optional) throws Exception {
//                Long sum = 0L;
//                for (Long a : longs){
//                    sum += a;
//                }
//                Long b = optional.isPresent() ? optional.get(): 0L;
//                return Optional.<Long>of(sum +b);
//            }
//        }).print();



        jssc.start();
        jssc.awaitTermination();
    }



}