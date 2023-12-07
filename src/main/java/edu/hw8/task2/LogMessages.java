package edu.hw8.task2;

public final class LogMessages {
    public static final String INTERRUPTED = "Thread - {} - was interrupted, exception message: {}";
    public static final String FINISHED = "Thread - {} - has finished the job";

    public static final String ADDING_TASK = "Thread - {} - is trying to put the task";
    public static final String ADDED_TASK = "Thread - {} - has put the task";

    public static final String GETTING_TASK = "Thread - {} - is trying to get the task";
    public static final String NO_TASK_AVAILABLE = "Thread - {} - cannot get any task. Currently no task available";
    public static final String HAS_TASK = "Thread - {} - has got the task. Executing...";
    public static final String EXECUTED_TASK = "Thread - {} - has executed the task";

    private LogMessages() {
    }
}
