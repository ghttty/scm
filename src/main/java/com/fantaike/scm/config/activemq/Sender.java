package com.fantaike.scm.config.activemq;

import com.fantaike.scm.config.datasource.DataSourceContext;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

//@Service
public class Sender {
	public static final Logger log = LoggerFactory.getLogger(DataSourceContext.class);

//	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	public void publish(String destName, Object message) {
		JmsTemplate jmsTemplate = jmsMessagingTemplate.getJmsTemplate();
		//开启订阅模式
		jmsTemplate.setPubSubDomain(true);
		Destination destination = new ActiveMQTopic(destName);
		System.out.println("发布消息：" + message);
		jmsMessagingTemplate.convertAndSend(destination, message);
	}
}
