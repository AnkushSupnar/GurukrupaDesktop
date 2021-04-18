package hibernate.dao.dao;

import java.util.List;

import hibernate.entities.Counter;

public interface CounterDao {

	public Counter getCounterById(int id);
	public Counter getCounterByName(String name);
	public Counter getCounterByPerson(String person);
	public List<Counter>getAllCounter();
	public char getBillInitial(int counterId);
	public int saveCounter(Counter counter);
	
}
