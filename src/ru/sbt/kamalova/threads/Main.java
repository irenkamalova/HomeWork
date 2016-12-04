package ru.sbt.kamalova.threads;

/**
 * Created by Irina Kamalova on 04.12.16.
 */
public class Main {
    public static void main(final String[] args) throws InterruptedException {
        Runnable[] runnables = new Runnable[10];

        runnables[0] = new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        };
        for (int i = 1; i < 10; i++) {
            runnables[i] = new MyRunnable(i);
        }

        Runnable finish = new Runnable() {
            @Override
            public void run() {
                System.out.println("Finish");
            }
        };

        ExecutionManager em = new ExecutionManagerImpl();
        Context context = em.execute(finish, runnables);
        System.out.println("Completed: " + context.getCompletedTaskCount());
        Thread.sleep(10, 0);
        System.out.println("Completed: " + context.getCompletedTaskCount());
        Thread.sleep(20, 0);
        System.out.println("Completed: " + context.getCompletedTaskCount());
        context.interrupt();
        System.out.println("Failed: " + context.getFailedTaskCount());
        System.out.println("Interrupted: " + context.getInterruptedTaskCount());
        System.out.println("Finished: " + context.isFinished());
        /*
        Completed: 2
        Completed: 4
        Completed: 5
        Failed: 1
        Interrupted: 4
        Finished: false
        Finish
         */
    }
}


