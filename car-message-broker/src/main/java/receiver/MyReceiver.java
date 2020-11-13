package receiver;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class MyReceiver {

	public static void main(String[] args) {
		try{
			
			//ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			//QueueConnectionFactory factory = (QueueConnectionFactory) applicationContext.getBean("connectionFactory");
			
			//Queue queue = (Queue) applicationContext.getBean("queue");
			
			// Create a connection. See https://docs.oracle.com/javaee/7/api/javax/jms/package-summary.html

			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = factory.createConnection("admin", "admin");

			// Open a session

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


			// start the connection
			connection.start();

			Destination destination = session.createQueue("CarQueue");
			//Destination destination = session.createQueue("QueueTest");

			// Create a receiver

			MessageConsumer consumer = session.createConsumer(destination);

			// Receive the message

			while(true){
				Message msg = consumer.receive();
				String body = ((TextMessage) msg).getText();

				System.out.println("Received = " + body);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			// todo close session
		}
	}

}
