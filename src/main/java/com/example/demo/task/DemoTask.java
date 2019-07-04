package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.*;

/**
 * @Author limeng
 * @Description 发送数据入数据仓库
 * @Date @Date: 2018/10/13 10:05
 * @Modified by :
 **/
@Component
public class DemoTask {
    private static final Logger logger = LoggerFactory.getLogger(DemoTask.class);

    private static ExecutorService threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());


    private static final String TASK_NAME = "sync data to bank task";
    /**
     * 发送数据到银行数据仓库
     */
    //@Scheduled(cron = "0 0/20 8-23 * * ?")
    public void sysnDataToBankTask() {
        Long startTime = System.currentTimeMillis();
        logger.debug(TASK_NAME + " start：{}", new Date());
        // 总共24张表 分四组
        try {

            CountDownLatch countDownLatch = new CountDownLatch(4);
            Collection<SyncBankThread> syncBankThreads = new ArrayList();
            threadPool.invokeAll(syncBankThreads);
            countDownLatch.await();
        } catch (InterruptedException e) {
            logger.error(TASK_NAME + "--son thread --" + Thread.currentThread().getId() + " error" , e);
        } catch (Exception e) {
            logger.error(TASK_NAME + "--son thread --" + Thread.currentThread().getId() + " error:" + e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug(TASK_NAME + " end：{}, total cost time:{} ms", new Date(), (endTime - startTime));
    }

    public class SyncBankThread implements Callable<SyncBankThread> {

        private List<String> tableNames;
        private CountDownLatch countDownLatch;

        public SyncBankThread(List<String> tableNames, CountDownLatch countDownLatch) {
            this.tableNames = tableNames;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public SyncBankThread call() {
            Long startTime = System.currentTimeMillis();
            long threadId = Thread.currentThread().getId();
            logger.debug(TASK_NAME + "--son thread --" + threadId + " start：{}", new Date());
            try {

            } catch (Exception e) {
            } finally {
                countDownLatch.countDown();
                Long endTime = System.currentTimeMillis();
                logger.info(TASK_NAME + "--son thread --" + threadId +" end, cost time：{} ms，now the countDown:{}", (endTime-startTime), countDownLatch.getCount());
            }
            return null;
        }
    }

}
