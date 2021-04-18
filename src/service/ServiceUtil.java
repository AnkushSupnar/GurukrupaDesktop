package service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceUtil {

	public static HttpResponse<String> response;
	public static HttpClient client = HttpClient.newBuilder().build();
	public static HttpRequest request;

	public static HttpResponse<String> callRestApiGet(String url){
	   // url = url.replaceAll(" ", "%20");
	    System.out.println("Got in Service util chages "+url);
		//request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
		request =HttpRequest.newBuilder(URI.create(url))
				.header("Content-Type", "application/json")
				.GET().build();
		response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}
	
	public static HttpResponse<String> callRestApiPost(String url,String inputJson)
			throws IOException, InterruptedException
	{
		request = HttpRequest.newBuilder(URI.create(url))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
		  response = 
				client.send(request,HttpResponse.BodyHandlers.ofString());
		return response;
	}
	public static HttpResponse<String>callRestApiPut(String url,String inputJson) throws IOException, InterruptedException
	{
		request = HttpRequest.newBuilder(URI.create(url))
				.header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
		response = 
				client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}
	
}
