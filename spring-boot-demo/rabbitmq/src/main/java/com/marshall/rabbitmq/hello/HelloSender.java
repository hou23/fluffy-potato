package com.marshall.rabbitmq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author yaojie.hou
 * @create 2018/5/28
 */
@Component
public class HelloSender {

	private AmqpTemplate rabbitTemplate;

	@Autowired
	public HelloSender(AmqpTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send() {
		String context = "hello " + LocalDateTime.now().toString();
		System.out.println("Sender: " + context);
		rabbitTemplate.convertAndSend("hello", context);
	}
}
