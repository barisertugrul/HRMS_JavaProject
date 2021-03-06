package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.UserValidationService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private UserValidationService<Employer> validationService;
	
	@Autowired
	public EmployerManager(EmployerDao employerDao, UserValidationService<Employer> validationService) {
		super();
		this.employerDao = employerDao;
		this.validationService = validationService;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.findAll(),"Employers listed");	
	}

	@Override
	public Result add(Employer employer) {
		var result = this.employerDao.save(employer);
		if(result != null) {
			return new SuccessResult(Messages.userAddedSuccess);
		}
		return new ErrorResult();
	}

	@Override
	public Result checkLogin(String email, String password) {
		Employer employer = this.employerDao.findEmployerByEmailAndPassword(email, password);
		if(employer != null) {
			if(employer.isActive() && employer.isAdminConfirm() && !employer.isDeleted()) {
				return new SuccessResult();
			}
		}
        return new ErrorResult();
	}

	@Override
	public Result validate(Employer employer) {
		var errors = validationService.validate(employer);
		
		if(errors != null) {
			String message = "";
			for (String error : errors.getData()) {
				message += error + "\n";
			}
			return new ErrorResult(message);
		}
		return new SuccessResult();
	}

	@Override
	public Result verifyByAdmin(Employer employer) {
		employer.setAdminConfirm(true);
		var result = employerDao.save(employer);
		if(result != null) {
			return new SuccessResult(Messages.employerVerifiedByAdmin);
		}
		return new ErrorResult();
	}

	@Override
	public boolean isActive(int employerId) {
		Employer employer = this.employerDao.getById(employerId);
		return employer.isActive() && employer.isAdminConfirm() && !employer.isDeleted();
	}
}
