package com.marshall.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yaojie.hou
 * @create 2018/5/29
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicReceiver1 {

	@RabbitHandler
	public void process(String s) {
		System.out.println("Topic Receiver 1: " + s);
	}
}
