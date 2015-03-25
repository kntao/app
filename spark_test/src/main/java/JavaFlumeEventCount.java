import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qingtao.kong on 2015/3/16.
 */
public class JavaFlumeEventCount {
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
