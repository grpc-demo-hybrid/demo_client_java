package dojo;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Fanmao.Li
 * @since 12/09/2019
 */
public class ThreadLocalTest {
    public static ThreadLocal<String> traceId = ThreadLocal.withInitial(() -> {
        System.out.println("Thread local 初始化。");
        return "123";
    });

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadLocalTest.traceId.get());
                /**
                 * 1. 线程池环境下使用ThreadLocal必须做好清理, 如果不清理会产生严重的后果：1.给后续线程留下垃圾数据 2. 造成内存溢出。
                 * 2. 进行ThreadLocal清理时一定要小心，如下面这段代码，如果在清理之前程序发生了异常则可能导致清理代码没有被执行，从而导致ThreadLocal没有被清理。
                 */
                if (LocalDateTime.now().getDayOfMonth() > 10) {
                    throw new RuntimeException("这个时间不允许运行。");
                }
                ThreadLocalTest.traceId.remove();
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadLocalTest.traceId.get());
                ThreadLocalTest.traceId.remove();
            }
        };

        threadPool.submit(t1);
        threadPool.submit(t2);
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {}
    }
}


