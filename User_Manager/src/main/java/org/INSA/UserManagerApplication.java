package org.INSA;

import java.net.MalformedURLException;
import javax.xml.ws.Endpoint;

public class UserManagerApplication {
    public static String host = "localhost";
    public static short port = 8089;

    public void startService() {
        String url = "http://" + host + ":" + port + "/";
        Endpoint.publish(url, new UserManagerWS());
    }

    public static void main (String[] args ) throws MalformedURLException {
        new UserManagerApplication().startService();
        System.out.println("Service started");
    }
}