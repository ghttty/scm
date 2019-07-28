package com.fantaike.scm.config.activemq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

//@Configuration
public class ActiveMqConfig {

//	@Bean
	JmsListenerContainerFactory<?> myListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleJmsListenerContainerFactory simpleJmsListenerContainerFactory = new SimpleJmsListenerContainerFactory();
		simpleJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
		//开启订阅模式
		simpleJmsListenerContainerFactory.setPubSubDomain(true);
		//开启持久化订阅,订阅端不在线能保持未消费的消息
		simpleJmsListenerContainerFactory.setClientId("asd");
		simpleJmsListenerContainerFactory.setSubscriptionDurable(true);
		return simpleJmsListenerContainerFactory;
	}
}
