package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {

	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>
		(this.employeeDao.findAll());
	}

	@Override
	public Result add(Employee employe) {
		var result = employeeDao.save(employe);
		if(result != null) {
			return new SuccessResult(Messages.userAddSuccess);
		}
		return new ErrorResult();
	}

	@Override
	public Result checkLogin(String email, String password) {
		Employee employee = this.employeeDao.findUserByEmailAndPassword(email, password);
			if(employee != null) {
				if(employee.isActive() && !employee.isDeleted()) {
					return new SuccessResult();
				}
			}
	        return new ErrorResult();
		
	}
}
