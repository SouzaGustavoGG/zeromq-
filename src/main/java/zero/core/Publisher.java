package zero.core;

import java.util.Scanner;
import java.util.logging.Logger;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ.Socket;

/**
* Pubsub envelope publisher
*/
public class Publisher{
	
	private static Logger logger = Logger.getLogger(Publisher.class.getName());
	
	private int port;
	
	public Publisher(int port) {
		this.port = port;
	}

    public void pub() throws Exception{
    	
		new Thread() {
			private Scanner in;

			@Override
			public void run() {
		    	
		        try (ZContext context = new ZContext()) {
	            	in = new Scanner(System.in);
		            Socket publisher = context.createSocket(SocketType.PUB);
		            publisher.bind("tcp://*:" + String.valueOf(port));

		            while (!Thread.currentThread().isInterrupted()) {
		            	System.out.println("Select topic: " + getTopics());

		                Topic topic = Topic.valueOf(in.nextLine().toUpperCase());
		            	System.out.println("Write message: ");
		            	String msg = in.nextLine();
		            	
		            	sendMsg(publisher, topic, msg);
		            	
		            }
		        }	
			}
		}.start();
    }
    
    private void sendMsg(Socket publisher, Topic topic, String msg) {
    	System.out.println("Sending message-> " + topic.name() + " to topic-> " + msg);
        publisher.sendMore(topic.name());
        publisher.send(msg);
        System.out.println("********************************************************");
    }
    
	private String getTopics() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Topic t : Topic.values()) {
			sb.append(t.name() + " ");
		}
		sb.append("]");
		return sb.toString();
	}
}
