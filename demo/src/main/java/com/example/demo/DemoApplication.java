package com.example.demo;

import io.micrometer.core.aop.TimedAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableConfigurationProperties(Config.class)
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {
	private final UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public TimedAspect timedAspect() {
		return new TimedAspect();
	}
	@EventListener(ApplicationStartedEvent.class)
	public void insert() {
		userRepository.save(new User().setEmail("email" + "@db.com").setName("name" ).setId(1L));
	}

//	@Bean
//	@ConfigurationProperties(prefix = "pool")
//	public ThreadPoolTaskExecutor exportPool() {
//		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
//		threadPoolTaskExecutor.setCorePoolSize(size);
//		return threadPoolTaskExecutor;
//	}
}
