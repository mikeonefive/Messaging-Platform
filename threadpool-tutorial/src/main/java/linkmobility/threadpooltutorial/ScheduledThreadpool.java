
package linkmobility.threadpooltutorial;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadpool {

    public static void main(String[] args) {

        int threadCount = 3;
        int initialDelay = 0;
        int interval = 5; // how frequently to execute the task after first run
        int totalNumberOfTasks = 5;
        int secondsToShutdown = 3;

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(threadCount);

        // schedule tasks with a fixed time interval
        scheduleTasks(initialDelay, interval, totalNumberOfTasks, threadPool);

        // shutdown the executor service after a certain period
        scheduleShutdown(threadPool, secondsToShutdown);
    }

    private static void scheduleTasks(int initialDelay, int interval, int totalNumberOfTasks, ScheduledExecutorService threadPool) {

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
    }

    private static void scheduleShutdown(ScheduledExecutorService threadPool, int secondsToShutdown) {
        threadPool.schedule(() -> {
            System.out.println("Shutting down executor service after the specified duration.");
            threadPool.shutdown();
        }, secondsToShutdown, TimeUnit.SECONDS); // Adjust the duration as needed
    }
}