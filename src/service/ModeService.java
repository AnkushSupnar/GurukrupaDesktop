package service;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import hibernate.entities.Mode;

public class ModeService {
	HttpResponse<String> response;
	String url = "http://localhost:8080/api/modes";

	public Mode getModeById(long id) {
		try {
			response = ServiceUtil.callRestApiGet(url + "/byid/" + id);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Mode.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Mode> getAllModes() {
		try {
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Mode>>() {
				});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Mode getModeByCustomer(long customerid) {
		try {
			response = ServiceUtil.callRestApiGet(url + "/bycustomer/" + customerid);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Mode.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Mode getModeByLogin(int loginid) {
		try {
			response = ServiceUtil.callRestApiGet(url + "/bylogin/" + loginid);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Mode.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Mode> getDateWiseModes(LocalDate date) {
		try {
			response = ServiceUtil.callRestApiGet(url + "/bydate/" + date);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Mode>>() {
				});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Mode saveMode(Mode mode) {
		try {
			response = ServiceUtil.callRestApiPost(url + "/save", JsonUtil.convertFromObjectToJson(mode));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Mode.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Mode updateMode(Mode mode) {
		try {
			response = ServiceUtil.callRestApiPost(url + "/update", JsonUtil.convertFromObjectToJson(mode));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Mode.class);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
