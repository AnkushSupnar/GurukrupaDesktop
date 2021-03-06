package hibernate.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Bill {
	
	long id;
	LocalDate date;
	String billno;
	double amount;
	double paidamount;
	Customer customer;
	Bank bank;
	Login login;
	List<Transaction>transaction = new ArrayList<>();

	public Bill() {
		super();
	}

	public Bill(long id,LocalDate date, String billno, double amount, double paidamount, Customer customer, Bank bank, Login login,
			List<Transaction> transaction) {
		super();
		this.id = id;
		this.date = date;
		this.billno = billno;
		this.amount = amount;
		this.paidamount = paidamount;
		this.customer = customer;
		this.bank = bank;
		this.login = login;
		this.transaction = transaction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(double paidamount) {
		this.paidamount = paidamount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", date=" + date + ", billno=" + billno + ", amount=" + amount + ", paidamount="
				+ paidamount + ", customer=" + customer + ", bank=" + bank + ", login=" + login + ", transaction="
				+ transaction + "]";
	}

	
	
	
}
