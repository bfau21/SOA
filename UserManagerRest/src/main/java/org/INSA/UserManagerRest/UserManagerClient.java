package org.INSA.UserManagerRest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class UserManagerClient {
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		String url = "http://localhost:8080/UserManagerRest/webapi/users/add/userTest/1/password";
		Entity<String> emptyEntity = Entity.entity("", MediaType.TEXT_PLAIN);
		Response response = client.target(url).request().put(emptyEntity);
		System.out.println("Statut : " + response.getStatus());
		System.out.println(response.readEntity(String.class));
		client.close();
	}
}
