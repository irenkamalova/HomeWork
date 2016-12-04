package ru.sbt.kamalova.threads;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Irina Kamalova on 30.11.16.
 */
public class ContextImpl implements Context {
    private List<Future> futures;
    private int canceledCount = 0;

    public ContextImpl(List<Future> futures) {
        this.futures = futures;
    }

    @Override
    public int getCompletedTaskCount() {
        int count = 0;
        for (Future<?> future : futures) {
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get(1, TimeUnit.MINUTES);
                    count++;
                } catch (Exception e) {
                    // No operation
                }
            }
        }
        return count;
    }

    @Override
    public int getFailedTaskCount() {
        int failedCount = 0;
        for (Future<?> future : futures) {
            try {
                future.get(1, TimeUnit.MINUTES);
            } catch (CancellationException e) {
                // No operation
            } catch (Exception e) {
                failedCount++;
            }
        }
        return failedCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        return canceledCount;
    }

    @Override
    public void interrupt() {
        for (Future<?> future : futures) {
            if(future.cancel(false)) {
                canceledCount++;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return getFailedTaskCount() == 0;
    }
}
