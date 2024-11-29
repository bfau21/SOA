package org.INSA.RestProject;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;


public class ClientRest {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		String url = "http://localhost:8080/RestProject/webapi/comparator/4";
		Entity<String> emptyEntity = Entity.entity("", MediaType.TEXT_PLAIN);
		Response response = client.target(url).request().put(emptyEntity);
		System.out.println("Statut : " + response.getStatus());
		System.out.println(response.readEntity(String.class));
		client.close();
	}
}