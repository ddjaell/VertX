package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle{ //Implementing a verticle by extending AbstractVerticle class
	
	 	@Override  //start() method is called when verticle is deployed to a Vert.x
	    public void start(Future<Void> startFuture) {
	 		//in start method you usally create TCP server or HTTP server.
	 		//register event handler to event bus, deploy other verticles, whatever verticles need to do
	        System.out.println("MyVerticle started!");
	    }

	 	
	    @Override //stop() method is called when Vert.x shut down.
	    public void stop(Future stopFuture) throws Exception {
	        System.out.println("MyVerticle stopped!");
	    }

}
