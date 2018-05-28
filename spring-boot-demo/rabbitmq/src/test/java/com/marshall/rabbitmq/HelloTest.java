package com.marshall.rabbitmq;

import com.marshall.rabbitmq.hello.HelloSender;
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
@SpringBootTest(classes = Application.class)
public class HelloTest {

	@Autowired
	private HelloSender helloSender;

	@Test
	public void hello() {
		helloSender.send();
	}
}
