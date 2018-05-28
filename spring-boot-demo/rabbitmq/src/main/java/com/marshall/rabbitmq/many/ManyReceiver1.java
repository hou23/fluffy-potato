package com.marshall.rabbitmq.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yaojie.hou
 * @create 2018/5/28
 */
@Component
@RabbitListener(queues = "many")
public class ManyReceiver1 {

	@RabbitHandler
	public void process(String s) {
		System.out.println("Receiver 1: " + s);
	}
}
