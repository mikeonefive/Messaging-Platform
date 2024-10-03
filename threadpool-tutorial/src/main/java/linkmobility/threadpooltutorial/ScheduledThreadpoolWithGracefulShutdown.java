
package linkmobility.threadpooltutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadpoolWithGracefulShutdown {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledThreadpoolWithGracefulShutdown.class);


    public static void main(String[] args) {

        int threadCount = 3;
        int initialDelay = 0;
        int interval = 5; // How frequently to execute the task after the first run
        int totalNumberOfTasks = 5;
        int secondsToShutdown = 3;

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(threadCount);

        scheduleTasks(threadPool, totalNumberOfTasks, initialDelay, interval);

        scheduleShutdown(threadPool, secondsToShutdown);
    }

    // Function to schedule the repetitive tasks
    private static void scheduleTasks(ScheduledExecutorService threadPool, int totalTasks, int initialDelay, int interval) {

        for (int i = 0; i < totalTasks; i++) {
            int currentTaskNumber = i;
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

    // Function to schedule the shutdown process
    private static void scheduleShutdown(ScheduledExecutorService threadPool, int secondsToShutdown) {
        threadPool.schedule(() -> {
            System.out.println("Shutting down executor service after the specified duration.");
            shutdownThreadPool(threadPool, secondsToShutdown);
        }, secondsToShutdown, TimeUnit.SECONDS);
    }

    // Function to handle the thread pool shutdown gracefully
    private static void shutdownThreadPool(ScheduledExecutorService threadPool, int secondsToShutdown) {
        threadPool.shutdown(); // Initiates shutdown

        try {
            if (!threadPool.awaitTermination(secondsToShutdown, TimeUnit.SECONDS)) {
                logger.info("Threadpool did not terminate in the specified time.");
                List<Runnable> droppedTasks = threadPool.shutdownNow();
                logger.info("Threadpool was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed.");
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow(); // Force shutdown on interruption
            Thread.currentThread().interrupt(); // Preserve interrupt status because otherwise thread "forgets" that it was interrupted after exception is caught
        }
    }
}