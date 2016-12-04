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
        final List<Future> futures = new ArrayList<>();
        final Runnable cb = callback;

        for (Runnable runnable : tasks) {
            Future<?> future = service.submit(runnable);
            futures.add(future);
        }
        Context context = new ContextImpl(futures);

        service.shutdown();

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                for (Future<?> future : futures) {
                    while (!future.isDone()) {
                        // No operation
                    }
                }
                cb.run();
            }
        });
        es.shutdown();
        return context;
    }
}
