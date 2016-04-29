import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by qingtao.kong on 2016/2/18.
 */
public class AsyncTaskSimple {
    public static class Result implements Callable<String>{

        @Override
        public String call() throws Exception {
            Thread.sleep(5*1000);
            return new String("call return!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FutureTask<String> future = new FutureTask<String>(new Result());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(future);
        doSomeThing();
        try{
            String s = future.get();
            System.out.println("the result is:" + s);

        }catch (Exception ex){

        }


    }
    private  static void doSomeThing() throws InterruptedException {
        System.out.println("do something");
        Thread.sleep(2*1000);
    }
}
