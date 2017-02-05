/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author TAKEN
 */
public class StartHandler{
    public StartHandler() throws IOException {
        HttpServer handler = HttpServer.create(new InetSocketAddress(9090), 0);
        handler.createContext("/req", new NMHandler());
        handler.setExecutor(null);
        handler.start();
    }
}
