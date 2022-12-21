package loan.management.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThreadUtil {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);

    public static void runAsync(Runnable runnable) {
        EXECUTOR.execute(runnable);
    }
}