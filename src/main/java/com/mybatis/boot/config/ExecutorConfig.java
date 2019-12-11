package com.mybatis.boot.config;

import com.mybatis.boot.asyn.util.VisiableThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author LX
 * @Date 2019/12/10 18:02
 * @Description �����̳߳�
 */

@Configuration
@Slf4j
public class ExecutorConfig {
    @Bean
    public AsyncTaskExecutor asyncServiceExecutor() {
        log.info("====================start asyncServiceExecutor==================");
        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        //���ú����߳���
        executor.setCorePoolSize(2);
        //��������߳���
        executor.setMaxPoolSize(5);
        //��Ծʱ��
        executor.setKeepAliveSeconds(60);
        //���ö��д�С
        executor.setQueueCapacity(100);
        //�����̳߳��е��̵߳�����ǰ׺
        executor.setThreadNamePrefix("async-");
        // rejection-policy����pool�Ѿ��ﵽmax size��ʱ����δ���������
        // CALLER_RUNS���������߳���ִ�����񣬶����е��������ڵ��߳���ִ��
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //ִ�г�ʼ��
        executor.initialize();
        return executor;
    }
}

