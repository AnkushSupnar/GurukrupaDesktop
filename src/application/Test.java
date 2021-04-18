package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import hibernate.entities.Counter;
import hibernate.entities.Item;



public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		String url="http://localhost:8080/api/counters/1";
		ObjectMapper objectMapper = new ObjectMapper();
		
		Counter counter = objectMapper.readValue(callRestApi(url).body(), Counter.class);
		//System.out.println(counter.toString());
		
		url = "http://localhost:8080/api/items";
		//System.out.println(callRestApi(url).statusCode());
		//System.out.println(callRestApi(url).body());
		List<Item>list = objectMapper.readValue(callRestApi(url).body(), new TypeReference<List<Item>>() {});
		//list.forEach(x-> System.out.println(x.toString()));

		url="http://localhost:8080/api/items/allnames";
		List<String> names = objectMapper.readValue(callRestApi(url).body(),new TypeReference<List<String>>(){});
		names.forEach(x-> System.out.println(x));
		
		url = "http://localhost:8080/api/items/getbyname/Gold%20Chain";
		System.out.println(callRestApi(url).body());
	}
	public static HttpResponse<String> callRestApi(String url)
	{
		//var url="http://localhost:8080/api/counters/1";
		var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		var client = HttpClient.newBuilder().build();
		HttpResponse<String> response = null;
		try {
			response = client.send(request,HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return response;
	}
}
