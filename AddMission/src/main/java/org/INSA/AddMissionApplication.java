package org.INSA;

import java.net.MalformedURLException;
import javax.xml.ws.Endpoint;

public class AddMissionApplication {
    public static String host = "localhost";
    public static short port = 8088;

    public void startService() {
        String url = "http://" + host + ":" + port + "/";
        Endpoint.publish(url, new AddMissionWS());
    }

    public static void main (String[] args ) throws MalformedURLException {
        new AddMissionApplication().startService();
        System.out.println("AddMission Service started");
    }
}