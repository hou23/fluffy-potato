package com.example.rocketmqdemo.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * Created by yaojie.hou on 2018/1/15.
 */
public class SyncProducer {

	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer =
				new DefaultMQProducer("marshall_rocketmq_test1");

		producer.start();
		for (int i = 0; i < 100; i++) {
			Message msg = new Message("marshall_topic1",
					"marshall_tag1",
					("marshall_body" + i).getBytes());

			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}

		producer.shutdown();
	}
}
