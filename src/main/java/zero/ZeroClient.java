package zero;

import java.util.logging.Logger;

import zero.core.Subscriber;
import zero.core.Topic;

public class ZeroClient {
	
	private static Logger logger = Logger.getLogger(ZeroClient.class.getName());

	public static void main(String[] args) throws Exception {
		logger.info("New sub: Gabriel, METAL");
		Subscriber sub1 = new Subscriber("Gabriel", 5563, Topic.METAL.name());
		sub1.sub();
		
		logger.info("New sub: Gustavo, METAL");
		Subscriber sub2 = new Subscriber("Gustavo", 5563, Topic.METAL.name());
		sub2.sub();
		
		logger.info("New sub: Michele, PLASTIC");
		Subscriber sub3 = new Subscriber("Michele", 5563, Topic.PLASTIC.name());
		sub3.sub();
		
		logger.info("New sub: Evelyn, PAPER");
		Subscriber sub4 = new Subscriber("Evelyn", 5563, Topic.PAPER.name());
		sub4.sub();
	}
}
