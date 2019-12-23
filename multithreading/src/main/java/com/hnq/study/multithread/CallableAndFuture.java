package com.hnq.study.multithread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author henengqiang
 * @date 2019/05/20
 */
public class CallableAndFuture {

    public static void main(String[] args) {
        try {
//            simpleTest();
            perfectTest();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一个不好的地方，就是如果有多个线程，就要有多个Future对象和多个get语句，
     * 如果第一个get语句的线程没有结束，没有返回结果，那么该future.get()就会一直处于等待
     * 状态，那么它后面的线程任务如果比它提前完成了，也要一直等下去，直到前面线程返回结果
     * 了才会去获取下一个线程的结果。
     */
    private static void simpleTest() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 这里我们如果不需要线程返回结果，我们使用threadPool.execute(command);
        // 如果我们需要线程返回结果，就使用submit(Callable<T> task)方法
        // 我们使用future来拿到线程执行后的结果。
        Future<String> future = executorService.submit(new TaskA());
        System.out.println("等待结果...");
        System.out.println("线程执行后的结果为: " + future.get());
        executorService.shutdown();
    }

    /**
     * 用于提交一组Callable任务，其take方法返回已经完成的一个Callable任务
     * 对应的Future对象。
     *
     * 这就好比我们同时煎了好多食材，然后等着把熟食材盛出来，盛食材的时候，应该是
     * 哪一个食材先熟了，则先去盛那个食材。
     *
     * completionService提交完多个任务之后，就可以等待返回结果，那个线程先返回
     * 结果，completionService的take就get到那个结果。
     */
    private static void perfectTest() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 10; i++) {
            TaskB taskB = new TaskB();
            taskB.setI(i);
            completionService.submit(taskB);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(completionService.take().get());
        }
        executorService.shutdown();
    }

    private static class TaskA implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Hello World!";
        }
    }

    private static class TaskB implements Callable<Integer> {
        private Integer i;
        void setI(Integer i) {
            this.i = i;
        }
        @Override
        public Integer call() throws Exception {
            Thread.sleep(new Random().nextInt(2000));
            return i;
        }
    }

}
