package com.cmu.project;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class GeneralHandler implements HttpHandler {
	
	protected final App server;
	public GeneralHandler(App server) {
		this.server = server;
	}
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain; charset=UTF-8");
		exchange.getResponseHeaders().put(Headers.KEEP_ALIVE, "timeout=500");
		exchange.getResponseHeaders().put(Headers.CONNECTION, "Keep-Alive");
		exchange.getResponseSender().send("Hello Testting!");
	}

	private void respond(HttpServerExchange exchange) {
		// TODO Auto-generated method stub
		
	}


}
