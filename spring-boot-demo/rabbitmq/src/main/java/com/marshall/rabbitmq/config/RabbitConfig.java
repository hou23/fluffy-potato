package com.marshall.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaojie.hou
 * @create 2018/5/28
 */
@Configuration
public class RabbitConfig {

	@Bean
	public Queue helloQueue() {
		return new Queue("hello");
	}

	@Bean
	public Queue manyQueue() {
		return new Queue("many");
	}
}
