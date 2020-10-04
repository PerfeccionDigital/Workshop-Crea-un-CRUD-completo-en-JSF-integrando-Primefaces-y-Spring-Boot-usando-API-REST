package com.perfecciondigital.workshop.repository;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfecciondigital.workshop.entity.Person;

public class PersonRepositoryImpl implements PersonRepository {

	private static final long serialVersionUID = -9040886899481503324L;

	private String url = "http://localhost:8082/person";
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Person> get() {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.get();
		JsonNode jsonNode = response.readEntity(JsonNode.class);
		try {
			return objectMapper.readerFor(new TypeReference<List<Person>>() {}).readValue(jsonNode.get("_embedded").get("person"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Person post(Person person) {
		JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Client client = ClientBuilder.newClient();
		client.register(jacksonJsonProvider);
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.post(Entity.entity(person, MediaType.APPLICATION_JSON));
		return response.readEntity(Person.class);
	}

	@Override
	public Person put(String id, Person person) {
		JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Client client = ClientBuilder.newClient();
		client.register(jacksonJsonProvider);
		WebTarget webTarget = client.target(url + "/" + id);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.put(Entity.entity(person, MediaType.APPLICATION_JSON));
		return response.readEntity(Person.class);
	}

	@Override
	public Person patch(String id, Person person) {
		JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Client client = ClientBuilder.newClient();
		client.register(jacksonJsonProvider);
		client.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);//for patch method
		WebTarget webTarget = client.target(url + "/" + id);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.method("PATCH", Entity.entity(person, MediaType.APPLICATION_JSON));
		return response.readEntity(Person.class);
	}
	
	@Override
	public void delete(String id) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url + "/" + id);
		Invocation.Builder invocationBuilder = webTarget.request();
		invocationBuilder.delete();
	}

}
