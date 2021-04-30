package service;

import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import hibernate.entities.Bank;

public class BankService {

	HttpResponse<String> response;
	String url = "";

	public BankService() {
		// TODO Auto-generated constructor stub
	}

	public Bank getBankById(int id) {
		try {
			url = "http://localhost:8080/api/banks/byid/" + id;
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Bank.class);
			else
				return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Bank> getAllBanks() {
		try {
			url = "http://localhost:8080/api/banks";
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bank>>() {
				});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllBankNames() {
		try {
			url = "http://localhost:8080/api/banks/allbanknames";
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<String>>() {
				});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public double getBankBalance(int id) {
		try {
			url = "http://localhost:8080/api/banks/getbalance/" + id;
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return Double.parseDouble(response.body());
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int addBankBalance(int id, double balance) {
		try {
			url = "http://localhost:8080/api/banks/addbalance/" + id + "/" + balance;
			response = ServiceUtil.callRestApiPut(url, "");
			if (response.statusCode() == 200)
				return 1;
			else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int reduceBankBalance(int id, double balance) {
		try {
			url = "http://localhost:8080/api/banks/reducebalance/" + id + "/" + balance;
			response = ServiceUtil.callRestApiPut(url, "");
			if (response.statusCode() == 200)
				return 1;
			else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Bank updateBank(Bank bank) {
		try {
			url = "http://localhost:8080/api/banks/update";
			response = ServiceUtil.callRestApiPut(url, JsonUtil.convertFromObjectToJson(bank));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Bank.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Bank saveBank(Bank bank) {
		try {
			url = "http://localhost:8080/api/banks/save";
			response = ServiceUtil.callRestApiPost(url, JsonUtil.convertFromObjectToJson(bank));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Bank.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
