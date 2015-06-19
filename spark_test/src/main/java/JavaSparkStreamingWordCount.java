/**
 * Created by qingtao.kong on 2015/4/1.
 */
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.flume.*;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.api.java.*;

import java.util.Arrays;
import java.util.List;

public class JavaSparkStreamingWordCount {

    public static void main(String[] args){
        List<byte[]> c = Arrays.asList("a".getBytes());
        get(c);
    }

    public static <T> void get(List<T> ts){
        for (T t : ts){
            t.getClass().getSimpleName();
        }
    }

}
