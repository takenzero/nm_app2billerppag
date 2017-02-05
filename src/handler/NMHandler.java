/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import inserttable.InsertToHandlerBiller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TAKEN
 */
public class NMHandler implements HttpHandler{
    private final String HND_ENCODING = "ISO-8859-1";
    ArrayList parseMsg = new ArrayList();
    private String prod;
    private String dest;
    
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response;
        //Do Something
        String qry;
        try (InputStream in = t.getRequestBody()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte buf[] = new byte[4096];
            for (int n = in.read(buf); n > 0; n = in.read(buf)) {
                out.write(buf, 0, n);
            }
            qry = new String(out.toByteArray(), HND_ENCODING);
            System.out.println(qry);
            
            Map<String,List<String>> parms = new HashMap<>();
            String defs[] = qry.split("[&]");
            for (String def: defs) {
                int ix = def.indexOf('=');
                String name;
                String value;
                if (ix < 0) {
                    name = URLDecoder.decode(def, HND_ENCODING);
                    value = "";
                }else{
                    name = URLDecoder.decode(def.substring(0, ix), HND_ENCODING);
                    value = URLDecoder.decode(def.substring(ix+1), HND_ENCODING);
                }
                List<String> list = parms.get(name);
                if (list == null) {
                    list = new ArrayList<>();
                    parms.put(name, list);
                }
                list.add(value);
            }
            
            String host           = t.getRemoteAddress().getHostName();
            List<String> message  = parms.get("request");
            String parseMessage   = message.get(0);
            String[] parts = parseMessage.split("[.]");
            this.prod = parts[0];
            this.dest = parts[1];
        
            int status         = 0;
            String description = "Inbox to handler from: "+host;
            //InsertToHandlerBiller ins = new InsertToHandlerBiller(host, parseMessage, this.prod, this.dest, status, description);
            //ins.start();
            InsertToHandlerBiller ins = new InsertToHandlerBiller(host, parseMessage, this.prod, this.dest, status, description);
            ins.start();
        } catch (SQLDataException ex) {
            Logger.getLogger(NMHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        response = "Transaksi Anda "+this.prod+"."+this.dest+" sedang diproses.";//set response to otomax
        t.sendResponseHeaders(200, response.length());
        System.out.println(response);
        try (OutputStream os = t.getResponseBody()) {
            os.write(response.getBytes());
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}