package ru.sbt.kamalova.threads;


/**
 * Created by Irina Kamalova on 30.11.16.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
