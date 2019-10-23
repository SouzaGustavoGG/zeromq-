package zero.core;

import java.util.logging.Logger;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

public class Subscriber {
	
	private static Logger logger = Logger.getLogger(Subscriber.class.getName());
	
	private String id;
	private int port;
	private String topic;
	
	public Subscriber(String id, int port, String topic) {
		this.id = id;
		this.port = port;
		this.topic = topic.toUpperCase();
	}

	public void sub(){
		new Thread(){
			@Override
			public void run() {
		        // Prepare our context and subscriber
		        try (ZContext context = new ZContext()) {
		            Socket subscriber = context.createSocket(SocketType.SUB);
		            subscriber.connect("tcp://localhost:" + String.valueOf(port));
		            subscriber.subscribe(topic.getBytes(ZMQ.CHARSET));

		            while (!Thread.currentThread().isInterrupted()) {
		                // Read envelope with address
		                String topic = subscriber.recvStr();
		                // Read message contents
		                String msg = subscriber.recvStr();
		                 
		                logger.info("Msg received: user->" + id +  " topic-> " + topic + " msg-> " + msg);
		            }
		        }
			}
		}.start();
    }
}
