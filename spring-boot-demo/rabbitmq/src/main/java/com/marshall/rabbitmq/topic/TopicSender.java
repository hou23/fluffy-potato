package com.marshall.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaojie.hou
 * @create 2018/5/29
 */
@Component
public class TopicSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send1() {
		String context = "hi, i'm message 1";
		rabbitTemplate.convertAndSend("exchange", "topic.message", context);
	}

	public void send2() {
		String context = "hi, i'm message 2";
		rabbitTemplate.convertAndSend("exchange", "topic.messages", context);
	}
}
