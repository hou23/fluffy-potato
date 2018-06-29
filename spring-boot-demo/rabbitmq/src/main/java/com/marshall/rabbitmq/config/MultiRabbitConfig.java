package com.marshall.rabbitmq.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaojie.hou
 * @create 2018/5/31
 *
 * 配置多个rabbitmq虚拟主机
 */
@Configuration
public class MultiRabbitConfig {

	@Bean
	public ConnectionFactory defaultConnectionFactory() {
		ConnectionFactory c = new ConnectionFactory();
		c.
	}

	@Bean
	public SimpleRabbitListenerContainerFactory defaultFactory() {
		SimpleRabbitListenerContainerFactory s = new SimpleRabbitListenerContainerFactory();
		s.setConnectionFactory();
	}
}
