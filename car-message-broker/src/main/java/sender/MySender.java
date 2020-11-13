package sender;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MySender {

	public static void main(String[] args) {
		
		try{
			
			//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			//QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			//Queue queue = (Queue) applicationContext.getBean("queue");

			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = factory.createConnection("admin", "admin");
			connection.start();

			// Open a session
			// start the connection

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// create producer
			//Destination destination = session.createQueue("QueueTest");
			Destination destination = session.createQueue("TopicsTest");
			MessageProducer producer = session.createProducer(destination);

			// send message
			TextMessage message = session.createTextMessage("Hello");
			producer.send(destination, message);


		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
