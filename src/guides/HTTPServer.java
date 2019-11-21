package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

public class HTTPServer extends AbstractVerticle {
	
	private HttpServer httpServer = null;
	
	@Override
	public void start() throws Exception{
		//when HTTPServer verticle is called, Vertx initiate start method. In start method, we create HTTP Server
		httpServer = vertx.createHttpServer();
		
		
		
		//Before starting HTTP Server with listen method, we need to set a request handler on the HTTP Server
		 httpServer.requestHandler(new Handler<HttpServerRequest>() {
	            @Override
	            public void handle(HttpServerRequest request) {
	            	
	                System.out.println("incoming request!");
	                //You can handle request header and parameters with HttpServerRequest class
	                System.out.println("request.uri() = " + request.uri());
	                //System.out.print("  request.path() = " + request.path());
	                //System.out.print("  request.getParam(p1) = " + request.getParam("p1"));
	                
	                //Handling POST method is a bit different
	                Buffer fullRequestBody = Buffer.buffer();
	                if(request.method() == HttpMethod.POST){

	                    request.handler(new Handler<Buffer>() {
	                        @Override
	                        public void handle(Buffer buffer) {
	                            fullRequestBody.appendBuffer(buffer);
	                        }
	                    });
	               
	                }
	                
	                HttpServerResponse response = request.response();
	                response.setStatusCode(200);
	                response.headers()
	                    .add("Content-Length", String.valueOf(57))
	                    .add("Content-Type", "text/html")
	                ;
	                response.write("Vert.x is alive!");
	                response.end();
	            }
	        });
		
		
		
		//starting HTTP Server with listen() method
		httpServer.listen(9999);
		
		System.out.println("HTTP Server is listening on 9999 port");
		//If there is more than one version of netty in your classpath, netty throws an exception. The exception looks something like below
		// 심각: java.lang.NoSuchMethodError: io.netty.bootstrap.ServerBootstrap.channelFactory(Lio/netty/channel/ChannelFactory;)Lio/netty/bootstrap/AbstractBootstrap;
		
		
	}

}
