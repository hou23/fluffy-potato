package com.marshall.rabbitmq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yaojie.hou
 * @create 2018/5/28
 */
@Component
public class ManySender2 {

	private final AmqpTemplate rabbitTemplate;

	@Autowired
	public ManySender2(AmqpTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void send(int i) {
		String context = "many2 " + " ***** " + i;
		rabbitTemplate.convertAndSend("many", context);
	}
}
