package hibernate.service.serviceImpl;

import java.util.List;

import hibernate.dao.dao.CounterDao;
import hibernate.dao.daoImpl.CounterDaoImpl;
import hibernate.entities.Counter;
import hibernate.service.service.CounterService;

public class CounterServiceImpl implements CounterService {

	private CounterDao dao;
	public CounterServiceImpl() {
		this.dao = new CounterDaoImpl();
	}
	@Override
	public Counter getCounterById(int id) {
		return dao.getCounterById(id);
	}

	@Override
	public Counter getCounterByName(String name) {
		return dao.getCounterByName(name);
	}

	@Override
	public Counter getCounterByPerson(String person) {
		return dao.getCounterByPerson(person);
	}

	@Override
	public List<Counter> getAllCounter() {
		return dao.getAllCounter();
	}

	@Override
	public char getBillInitial(int counterId) {
		return dao.getBillInitial(counterId);
	}

	@Override
	public int saveCounter(Counter counter) {
		return dao.saveCounter(counter);
	}

}
