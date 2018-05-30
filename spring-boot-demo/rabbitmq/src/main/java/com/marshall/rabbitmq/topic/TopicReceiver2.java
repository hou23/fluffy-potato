package com.marshall.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yaojie.hou
 * @create 2018/5/29
 */
@Component
@RabbitListener(queues = "topic.messages")
public class TopicReceiver2 {

	@RabbitHandler
	public void process(String s) {
		System.out.println("Topic Receiver 2: " + s);
	}
}
