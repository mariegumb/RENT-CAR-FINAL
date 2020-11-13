package com.tp.car.service;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;


@Service
public class JMSService {

    QueueSender producer;
    QueueSession session;
    Queue destination;

    public JMSService() {
        try{
            QueueConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            QueueConnection connection = factory.createQueueConnection("admin", "admin");
            connection.start();
            this.session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            this.destination = this.session.createQueue("CarQueue");
            this.producer = this.session.createSender(destination);
        } catch (JMSException e){
            e.printStackTrace();
        }
    }

    public void sendMessageToQueue(String message){
        try{
            TextMessage txtMessage = this.session.createTextMessage(message);
            this.producer.send(txtMessage, DeliveryMode.PERSISTENT, 4, 1000);
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
