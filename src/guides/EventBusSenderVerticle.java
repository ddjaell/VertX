package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class EventBusSenderVerticle extends AbstractVerticle{
	
	public void start(Future<Void> startFuture) {
		/*
		 * publish() method sends the message to all verticles listening on a given address
		 * send() method send the message to only one verticle listening to a specific address
		 */
        vertx.eventBus().publish("anAddress", "message 2");
        vertx.eventBus().send   ("anAddress", "message 1");
    }

}
