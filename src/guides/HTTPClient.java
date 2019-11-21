package guides;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;

public class HTTPClient extends AbstractVerticle{
	private HttpClient client = null;
	

	@Override
	public void start() throws Exception
	{
		client = vertx.createHttpClient();
	//getNow method is deprecated, but it is still working and I can't find any alternatives
		client.getNow(80, "tutorials.jenkov.com", "/", new Handler<HttpClientResponse>() {

		    @Override
		    public void handle(HttpClientResponse httpClientResponse) {
		        System.out.println("response code = " + httpClientResponse.statusCode());
		        
		        httpClientResponse.bodyHandler(new Handler<Buffer>() {
		            @Override
		            public void handle(Buffer buffer) {
		                System.out.println("Response (" + buffer.length() + "): ");
		                System.out.println(buffer.getString(0, buffer.length()));
		            }
		        });
		    }
		});
		
	}
}
