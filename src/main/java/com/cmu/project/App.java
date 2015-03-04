package com.cmu.project;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.*;
import io.undertow.util.Headers;
import java.net.UnknownHostException;
import java.net.InetAddress;
public class App {

        public App(int port) {

                GeneralHandler genHdl = new GeneralHandler(this);
                QueryHandler query = new QueryHandler(this);
                HttpHandler requestHandler = Handlers.path()
                                .addPrefixPath("/", genHdl)

                .addPrefixPath("/q1", query);
                Undertow server = null;
                try {
                server = Undertow.builder()
                                .setWorkerThreads(10)
                                .setIoThreads(32)
                                .addHttpListener(port, InetAddress.getLocalHost().toString().split("/")[1])
                .setHandler(requestHandler).build();
                } catch (UnknownHostException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        server.start();
        }

    public static void main(final String[] args) {
        App app = new App(80);
    }
}