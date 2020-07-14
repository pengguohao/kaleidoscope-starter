package com.pgh.kaleidoscope.core.boot.config;

import com.pgh.kaleidoscope.core.boot.props.KaleidoscopeAsyncProperties;
import lombok.AllArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步处理
 *
 * @author Chill
 */
@Configuration
@EnableAsync
@EnableScheduling
@AllArgsConstructor
@EnableConfigurationProperties({
	KaleidoscopeAsyncProperties.class
})
public class KaleidoscopeExecutorConfiguration extends AsyncConfigurerSupport {

	private final KaleidoscopeAsyncProperties kaleidoscopeAsyncProperties;

	@Override
	@Bean(name = "taskExecutor")
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(kaleidoscopeAsyncProperties.getCorePoolSize());
		executor.setMaxPoolSize(kaleidoscopeAsyncProperties.getMaxPoolSize());
		executor.setQueueCapacity(kaleidoscopeAsyncProperties.getQueueCapacity());
		executor.setKeepAliveSeconds(kaleidoscopeAsyncProperties.getKeepAliveSeconds());
		executor.setThreadNamePrefix("async-executor-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new SimpleAsyncUncaughtExceptionHandler();
	}

}
