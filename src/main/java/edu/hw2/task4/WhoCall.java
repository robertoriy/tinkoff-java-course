package edu.hw2.task4;

public final class WhoCall {
    private static final int CALLER_FRAME_NUMBER = 1;

    private WhoCall() {
    }

    public static CallingInfo callingInfo() {
        Throwable throwable = new Throwable();
        StackTraceElement[] stack = throwable.getStackTrace();

        if (stack.length < 2) {
            throw new RuntimeException("Something went wrong");
        }
        StackTraceElement caller = stack[CALLER_FRAME_NUMBER];

        return new CallingInfo(caller.getClassName(), caller.getMethodName());
    }
}
