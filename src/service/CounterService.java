package service;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import hibernate.entities.Counter;



public class CounterService {

	HttpResponse<String>response;
	String url="";
	public CounterService()
	{
		
	}
	public List<Counter>getAllCounters()
	{
		try {
			url = "http://localhost:8080/api/counters";
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Counter>>() {});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Counter getCounterById(int id)
	{
		try {
			url = "http://localhost:8080/api/counters/byid/"+id;
			Counter counter = null;
			response = ServiceUtil.callRestApiGet(url);
			counter = JsonUtil.convertFromJsonToObject(response.body(),Counter.class);
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Counter getCounterByName(String name)
	{
		try {
			url = "http://localhost:8080/api/counters/byname/"+name;
			//String path = url.replaceAll(" ", "%20");
			//System.out.println(path);
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
			return JsonUtil.convertFromJsonToObject(response.body(),Counter.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Counter getCounterByPerson(String name)
	{
		try {
			url = "http://localhost:8080/api/counters/byperson/"+name;
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
			return JsonUtil.convertFromJsonToObject(response.body(),Counter.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<String>getAllCounterNames()
	{
		try {
			url="http://localhost:8080/api/counters/allnames";
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
			
				return JsonUtil.convertFromJsonToList(response.body(),new TypeReference<List<String>>() {});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int saveCounter(Counter counter)
	{
		try {
			String json = JsonUtil.convertFromObjectToJson(counter);
			String url="http://localhost:8080/api/counters/save";
			HttpResponse<String> res = ServiceUtil.callRestApiPost(url, json);
			if(res.statusCode()==200)
			{
				return 1;
			}
			else 
				return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
