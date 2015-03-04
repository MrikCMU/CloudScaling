package com.cmu.project;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class QueryHandler implements HttpHandler{
	protected final App server;
	public QueryHandler(App server) {
		this.server = server;
	}
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain; charset=UTF-8");
		exchange.getResponseHeaders().put(Headers.KEEP_ALIVE, "timeout=500");
		exchange.getResponseHeaders().put(Headers.CONNECTION, "Keep-Alive");
		if (!exchange.getQueryParameters().containsKey("key") || !exchange.getQueryParameters().containsKey("message")) {
			exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
			exchange.setResponseCode(400);
			exchange.getResponseSender().send("Missing parameters.");
			return;
		}
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=sdfDate.format(new Date());
		String key = exchange.getQueryParameters().get("key").getFirst();
		String message = exchange.getQueryParameters().get("message").getFirst();
		Encrypt enc = new Encrypt();
		String msg = enc.getDecriptedMsg(enc.getArray(message), enc.getKey(key));
		exchange.getResponseSender().send("LudenSclouD8a,3840-9608-7526,5574-2236-0141,4374-7506-9598\n"+date+"\n"+msg+"\n");
	}
}
