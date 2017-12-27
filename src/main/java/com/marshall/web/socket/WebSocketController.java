package com.marshall.web.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by yaojie.hou on 2017/12/26.
 */
@Controller
public class WebSocketController {

	@MessageMapping("/welcome/{user}")
	@SendTo("/topic/getResponse")
	public String say(String something, @PathVariable String user) {
		System.out.println(something);
		return something;
	}
}
