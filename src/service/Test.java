package service;

import hibernate.entities.Login;

public class Test {
	public static void main(String[] args) {
		LoginService service = new LoginService();
		Login login = service.getLoginByName("Ankush%20Supnar");
		System.out.println(login);
		
	}
}
