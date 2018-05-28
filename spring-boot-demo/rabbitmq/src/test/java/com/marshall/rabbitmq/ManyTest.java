package com.marshall.rabbitmq;

import com.marshall.rabbitmq.many.ManySender1;
import com.marshall.rabbitmq.many.ManySender2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yaojie.hou
 * @create 2018/5/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyTest {

	@Autowired
	private ManySender1 manySender1;
	@Autowired
	private ManySender2 manySender2;

	@Test
	public void send() {
		for (int i = 0; i < 10; i++) {
			manySender1.send(i);
			manySender2.send(i);
		}
	}
}
