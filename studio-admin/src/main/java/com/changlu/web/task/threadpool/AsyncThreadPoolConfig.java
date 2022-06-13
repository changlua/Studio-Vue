package com.changlu.web.task.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsyncThreadPoolConfig
 * @Author ChangLu
 * @Date 4/15/2022 5:50 PM
 * @Description 异步线程任务配置器
 */
@Configuration
@EnableAsync
public class AsyncThreadPoolConfig{

    /**
     * 异步任务处理器
     * @return
     */
    @Bean("taskExecutor")
    public Executor getAsyncExecutor(){
        //使用Spring提供的线程池来进行创建
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);//最大核心数
        executor.setMaxPoolSize(8);//最大线程数
        executor.setQueueCapacity(10);//队列程度
        executor.setKeepAliveSeconds(1000);//空闲时间
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());//配置拒绝策略（丢弃掉最老的执行最新的）
        executor.setThreadNamePrefix("task-asyn");//设置线程的前缀名称
        return executor;
    }

}
