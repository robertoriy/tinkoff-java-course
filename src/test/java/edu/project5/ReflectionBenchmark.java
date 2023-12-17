package edu.project5;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State(Scope.Thread)
public class ReflectionBenchmark {
    private static final String METHOD_NAME = "name";
    private static final int FORKS = 1;
    private static final int WARMUP_FORKS = 1;
    private static final int WARMUP_ITERATIONS = 1;
    private static final int WARMUP_TIME = 5;
    private static final int MEASUREMENT_TIME = 120;
    private static final int MEASUREMENT_ITERATIONS = 1;
    private Student student;
    private Method method;
    private MethodHandle methodHandle;
    private Function<Student, String> lambdaMetaFactory;

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(FORKS)
            .warmupForks(WARMUP_FORKS)
            .warmupIterations(WARMUP_ITERATIONS)
            .warmupTime(TimeValue.seconds(WARMUP_TIME))
            .measurementIterations(MEASUREMENT_ITERATIONS)
            .measurementTime(TimeValue.seconds(MEASUREMENT_TIME))
            .build();
        new Runner(options).run();
    }

    @Setup
    @SuppressWarnings("unchecked")
    public void setup() throws Throwable {
        student = new Student("John", "Doe");

        method = Student.class.getDeclaredMethod(METHOD_NAME);
        method.setAccessible(true);

        methodHandle = MethodHandles.lookup().findGetter(Student.class, METHOD_NAME, String.class);

        lambdaMetaFactory = (Function<Student, String>) LambdaMetafactory.metafactory(
            MethodHandles.lookup(),
            "apply",
            MethodType.methodType(Function.class),
            MethodType.methodType(Object.class, Object.class),
            MethodHandles.lookup().findVirtual(Student.class, METHOD_NAME, MethodType.methodType(String.class)),
            MethodType.methodType(String.class, Student.class)
        ).getTarget().invokeExact();
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        String name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflectionAccess(Blackhole bh) throws Exception {
        Object name = method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandleAccess(Blackhole bh) throws Throwable {
        String name = (String) methodHandle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetaFactoryAccess(Blackhole bh) {
        String name = lambdaMetaFactory.apply(student);
        bh.consume(name);
    }

    record Student(String name, String surname) {
    }
}
