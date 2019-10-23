package zero;

import java.util.logging.Logger;

import zero.core.Publisher;

public class ZeroCompany {
	
	private static Logger logger = Logger.getLogger(ZeroCompany.class.getName());
	
	public static void main(String[] args) throws Exception {
		logger.info("Starting publisher (port: 5563)");
		
		Publisher pub = new Publisher(5563);
		pub.pub();
	}

}
