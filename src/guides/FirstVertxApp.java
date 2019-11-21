package guides;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;

public class FirstVertxApp {
	public static void main(String args[]) throws InterruptedException
	{
		//To use Vert.x, you must create Vertx instance first.
		//blow is creating Vertx instance with a single line.
		Vertx vertx = Vertx.vertx();
		
		//To use Vert.x properly, you need to deploy verticle to Vert.x
		//MyVerticle class is a sample Verticle class that extends AbstractVerticle class
		MyVerticle myverticle = new MyVerticle();
		//vertx.deployVerticle(myverticle); is when you deploy verticle to Vert.x
		
		//how to find out verticle is fully deployed
		/*vertx.deployVerticle(myverticle , new Handler<AsyncResult<String>>() {
		    @Override
		    public void handle(AsyncResult<String> stringAsyncResult) {
		        System.out.println("deployment fully completed");
		        }
		    });*/
		
		
		/*vertx.deployVerticle(myverticle, stringAsyncResult -> {
	        System.out.println("deployment complete");
		});*/
		
		
		/*  with lamda expression
		 *
		 *  vertx.deployVerticle(new BasicVerticle(), stringAsyncResult -> {
         *	System.out.println("BasicVerticle deployment complete");
		 *	});
		 */
		
		
		//also you can deploy other verticle to a verticle
		
		
		vertx.deployVerticle(new EventBusReceiverVerticle("R1")); //EventBus Receiver Verticle named R1
		vertx.deployVerticle(new EventBusReceiverVerticle("R2")); //EventBus Receiver Verticle named R2
		
		Thread.sleep(3000); //pause for 3000millsecs
		vertx.deployVerticle(new EventBusSenderVerticle()); //EventBus Sender Verticle
		
		
		//Creating a Buffer
		Buffer buffer = Buffer.buffer();
		
		//Creating a Buffer with some data in it
		byte[] initialData = new byte[]{1,2,3};
		Buffer buffer2 = Buffer.buffer(initialData);
		
		//Creating a Buffer with contents of a String
		Buffer buffer3 = Buffer.buffer("initial data");
		
		//Set encoding of a Buffer
		Buffer buffer4 = Buffer.buffer("inital data", "UTF-8");
		
		//printing buffer's length
		buffer2 = Buffer.buffer();
		System.out.println("buffer2.length() = " + buffer2.length());
		
		buffer2.setByte  ( 0, (byte)  127);
		buffer2.setShort ( 2, (short) 127);
		buffer2.setInt   ( 4,         127);
		buffer2.setLong  ( 8,         127);
		buffer2.setFloat (16,      127.0F);
		buffer2.setDouble(20,      127.0D);

		System.out.println("buffer2.length() = " + buffer.length());
		buffer2 = Buffer.buffer();
		buffer2.appendByte  ((byte)  127);
		buffer2.appendShort ((short) 127);
		buffer2.appendInt   (        127);
		buffer2.appendLong  (        127);
		buffer2.appendFloat (     127.0F);
		buffer2.appendDouble(     127.0D);
		
		System.out.println("buffer2.length() = " + buffer.length());
		
		
		
		//vertx.deployVerticle(new HTTPServer());
		//vertx.deployVerticle(new HTTPClient());
		//vertx.deployVerticle(new TCPServer());
		//vertx.deployVerticle(new TCPClient());
		vertx.deployVerticle(new Timers());
		
	}
}
