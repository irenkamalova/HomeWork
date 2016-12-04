package ru.sbt.kamalova.threads;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future> futures = new ArrayList<>();

        for (Runnable runnable : tasks) {
            Future<?> future = service.submit(runnable);
            futures.add(future);
        }
        Context context = new ContextImpl(futures);


        Thread thread = new Thread(callback);
        thread.start();

        service.shutdown();
        return context;
    }
}
