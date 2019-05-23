package com.example.demo;

import com.example.demo.ml.bean.Article;
import com.example.demo.ml.bean.Person;
import com.example.demo.ml.impl.DefaultWeChatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	/**
	 * 观察者模式
	 */
	@Test
	public void testObserve() {
		DefaultWeChatService weChat = new DefaultWeChatService();
		// 注册僵尸用户
		new Person("王震", 24).followWeChat(weChat);
		new Person("聂风", 24).followWeChat(weChat);
		new Person("小虎牙", 24).followWeChat(weChat);
		// 发布新文章
		weChat.publish(new Article("小安康居然。。。"));
		// 王者登录
		new Person("孟立", 24).login(weChat);
	}

}
