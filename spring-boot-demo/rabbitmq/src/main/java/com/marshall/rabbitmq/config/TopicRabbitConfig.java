package com.marshall.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaojie.hou
 * @create 2018/5/29
 */
@Configuration
public class TopicRabbitConfig {

	static final String message = "topic.message";
	static final String messages = "topic.messages";

	@Bean
	public Queue queueMessage() {
		return new Queue(message);
	}

	@Bean
	public Queue queueMessages() {
		return new Queue(messages);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	/**
	 * 用routingKey将exchange和queue绑定起来
	 * 发送到这个exchange上的消息根据routingKey
	 * @param queueMessage
	 * @param exchange
	 * @return
	 */
	@Bean
	Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
	}

	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
	}
}
