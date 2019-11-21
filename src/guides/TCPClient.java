package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

public class TCPClient extends AbstractVerticle{
	private NetClient tcpClient= null;
	
	@Override
	public void start() throws Exception
	{
		tcpClient = vertx.createNetClient();
		
		tcpClient.connect(10000, "localhost",
	            new Handler<AsyncResult<NetSocket>>(){

	            @Override
	            public void handle(AsyncResult<NetSocket> result) {
	                NetSocket socket = result.result();
	                socket.write("client is sending this message");
	                
	                socket.handler(new Handler<Buffer>(){
	                    @Override
	                    public void handle(Buffer buffer) {
	                        System.out.println("Client Received data length : " + buffer.length());

	                        System.out.println("Client Received data : " + buffer.getString(0, buffer.length()));

	                    }
	                });
	            }
	        });
	}
	
}
