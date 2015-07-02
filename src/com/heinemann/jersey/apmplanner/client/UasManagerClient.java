package com.heinemann.jersey.apmplanner.client;


import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

public class UasManagerClient {

	public static void main(String[] args) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(getBaseURI());
		
		//System.out.println(service.path("rest").path("uas").request()
		//		.accept(MediaType.TEXT_PLAIN).get(String.class));
		//System.out.println(service.path("rest").path("uas").request()
		//		.accept(MediaType.TEXT_XML).get(String.class));
		System.out.println(service.path("rest").path("uas").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class));
		System.out.println(service.path("rest").path("uas").request()
				.accept(MediaType.APPLICATION_XML).get(String.class));
		
		Form form = new Form();
		form.param("command", "reboot");
		Response response = service
				.path("rest")
				.path("uas")
				.request()
				.post(Entity
						.entity(form, MediaType.APPLICATION_FORM_URLENCODED),
						Response.class);
		System.out.println("Form response " + response.getStatus());
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://rigi-lab-03.cs.uvic.ca:8080/com.heinemann.jersey.apmplanner-0.0.1-SNAPSHOT").build();
	}
}
