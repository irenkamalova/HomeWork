package ru.sbt.kamalova.threads;

/**
 * Created by Irina Kamalova on 30.11.16.
 */
public interface Context {
    /**
     * Метод getCompletedTaskCount() возвращает количество тасков, которые на текущий момент успешно выполнились.
     * @return
     */
    int getCompletedTaskCount();
    int getFailedTaskCount();
    int getInterruptedTaskCount();
    void interrupt();
    boolean isFinished();
}
