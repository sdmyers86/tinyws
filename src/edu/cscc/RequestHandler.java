package edu.cscc;

import java.io.*;
import java.net.Socket;

/**
 * RequestHandler - handle HTTP GET Requests
 * (ignore anything else)
 * @author student name
 */
public class RequestHandler {
    private Socket connection;

    /**
     * Constructor
     */
    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    /**
     * Process an HTTP request
     */
    public void processRequest() throws IOException {
        // TODO code here remove throws
        try{
            String newRequest = readRequest();
            HTTPRequest req = new HTTPRequest(newRequest);
            ResponseHandler res = new ResponseHandler(req);
            res.sendResponse(connection);
        } finally {
            connection.close();
        }
    }

    // Read an HTTP Request
    private String readRequest() throws IOException {
        // Set socket timeout to 500 milliseconds
        connection.setSoTimeout(500);
        int recbufsize = connection.getReceiveBufferSize();
        InputStream in = connection.getInputStream();
        InputStreamReader rdr = new InputStreamReader(in);
        BufferedReader brdr = new BufferedReader(rdr, recbufsize);
        StringBuilder reqBuf = new StringBuilder();
        char[] cbuf = new char[recbufsize];
        
        // TODO code here
        brdr.read(cbuf);
        for(char c : cbuf) {
            reqBuf.append(c);
        }
        return reqBuf.toString();
        
//        // TODO - delete following statement
//        return(null);
    }
}