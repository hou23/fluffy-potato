package com.marshall.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaojie.hou
 * @create 2018/5/29
 */
@Component
public class FanoutSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hi, i'm fanout message";
		rabbitTemplate.convertAndSend("fanoutExchange", "", context);
	}
}
