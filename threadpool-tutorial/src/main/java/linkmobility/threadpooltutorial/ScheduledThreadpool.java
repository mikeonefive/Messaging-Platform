
package linkmobility.threadpooltutorial;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadpool {

    public static void main(String[] args) throws InterruptedException {

        int threadCount = 3;
        int initialDelay = 0;
        int interval = 5; // how frequently to execute the task after first run
        int totalNumberOfTasks = 5;
        int secondsToShutdown = 3;

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(threadCount);

        for (int i = 0; i < totalNumberOfTasks; i++) {
            int currentTaskNumber = i;

            // .execute expects an implementation of a Runnable so you need this lambda expression
            threadPool.scheduleAtFixedRate(
                    () -> {
                        String info = Thread.currentThread().getName() + ": Task# " + currentTaskNumber;
                        System.out.println(info);
                    },
                    initialDelay,
                    interval,
                    TimeUnit.SECONDS);
        }

        // shutdown the executor service after a certain period
        threadPool.schedule(() -> {
            System.out.println("Shutting down executor service after the specified duration.");
            threadPool.shutdown();
        }, secondsToShutdown, TimeUnit.SECONDS); // Adjust the duration as needed
    } 
}