package com.crazysusanin.planning.service;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {


 /** Количество основных потоков (количество потоков по умолчанию) */
    private static final int corePoolSize = 20;
 /** Максимальное количество потоков */
    private static final int maxPoolSize = 100;
 /** Разрешить время простоя потока (единица измерения: секунда по умолчанию) */
    private static final int keepAliveTime = 10;
 /** Размер очереди буфера */
    private static final int queueCapacity = 200;
 /** Префикс имени пула потоков */
    private static final String threadNamePrefix = "Async-Service-";

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix(threadNamePrefix);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }
}