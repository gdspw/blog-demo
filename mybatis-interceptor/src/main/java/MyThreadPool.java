/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */

import com.itstabber.blog.interceptor.example.MyRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/8/10 17:08
 * @since 1.0.0
 */
public class MyThreadPool {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 2;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),new ThreadPoolExecutor.DiscardPolicy());
        for(int i=0;i<20;i++){
            MyRunnable worker = new MyRunnable("线程："+i);
            poolExecutor.execute(worker);
        }

        //终止线程池
        poolExecutor.shutdown();
        while (!poolExecutor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

}
