package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;

public class TCPServer extends AbstractVerticle{
	
	
	private NetServer server = null;
	@Override
	public void start() throws Exception
	{
		//creating tcp Socket Server
		server = vertx.createNetServer();
		
		
		//before listen() method, you need to set connectHandler first
		server.connectHandler(new Handler<NetSocket>() {

            @Override
            public void handle(NetSocket netSocket) {
                System.out.println("Incoming connection!");
                
                
                netSocket.handler(new Handler<Buffer>() {

                    @Override
                    public void handle(Buffer buffer) {
                        System.out.println("incoming data length: "+buffer.length());

                        System.out.println("incoming data : "  + buffer.getString(0,buffer.length()));
                        
                        Buffer outBuffer = Buffer.buffer();
                        outBuffer.appendString("response...");
                        
                        netSocket.write(outBuffer);
                    }
                });
            }
            
            
        });
    
        server.listen(10000);
    }
	

}
