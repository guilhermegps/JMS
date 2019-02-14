package br.com.teste.jms;

import java.io.StringWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;

import br.com.teste.modelo.Pedido;
import br.com.teste.modelo.PedidoFactory;

public class TesteProdutorTopico {
	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topico = (Destination) context.lookup("loja");
		
		MessageProducer producer = session.createProducer(topico);
		
		Pedido pedido = PedidoFactory.geraPedidoComValores();
		
		StringWriter xml = new StringWriter();
		JAXB.marshal(pedido, xml);		
		Message message = session.createTextMessage(xml.toString());
//			message.setBooleanProperty("ebook", false);
		producer.send(message);
		
		session.close();
		connection.close();
		context.close();
	}
}
