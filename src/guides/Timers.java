package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;

public class Timers extends AbstractVerticle {
	//Vertx has 2 types of timers.
	
	
	
	@Override
	public void start() throws Exception{
		//first one is one-time timer
		//a timer that fires after a single time, milliseconds
		long timerID = vertx.setTimer(3000, new Handler<Long>() {

		    @Override
		    public void handle(Long aLong) {
		        System.out.println("Timer fired");
		    }
		});
		
		//second type is period timer
		//a timer that fires every time the specific time passes
		long periodtimerID = vertx.setPeriodic(3000, new Handler<Long>() {

		    @Override
		    public void handle(Long aLong) {
		        System.out.println("PeriodTimer fired: " + aLong);
		    }
		});
	}
	
}
