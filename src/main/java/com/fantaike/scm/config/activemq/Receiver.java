package com.fantaike.scm.config.activemq;

import com.fantaike.scm.config.datasource.DataSourceContext;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

//@Service
public class Receiver {

	public static final Logger log = LoggerFactory.getLogger(DataSourceContext.class);

	@JmsListener(destination = "test.topic", containerFactory = "myListenerContainerFactory")
	public void subscriber(Object obj) {
		try {
			//接受对象消息
			if (obj instanceof ActiveMQObjectMessage) {
				Object sourObj = ((ActiveMQObjectMessage) obj).getObject();

					System.out.println("收到订阅消息：" + sourObj);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
