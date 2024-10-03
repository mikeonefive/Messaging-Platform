
package linkmobility.threadpooltutorial;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SimpleThreadpool {

    public static void main(String[] args) {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        int totalNumberOfTasks = 5;

        // schedule the tasks
        for (int i = 0; i < totalNumberOfTasks; i++) {
            int currentTaskNumber = i;

            // .execute expects an implementation of a Runnable so you need this lambda expression
            threadPool.execute(() -> {
                String info = Thread.currentThread().getName() + ": Task# " + currentTaskNumber;
                System.out.println(info);
            });
        }

        // wait until all tasks finished???

        threadPool.shutdown();
    }
}
