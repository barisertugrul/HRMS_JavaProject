package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserValidationService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;
	private UserValidationService<JobSeeker> validationService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, UserValidationService<JobSeeker> validationService) {
		super();
		this.jobSeekerDao = jobSeekerDao;
		this.validationService = validationService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAll() {
		return new SuccessDataResult<List<JobSeeker>>
		(this.jobSeekerDao.findAll());	
	}

	@Override
	public Result add(JobSeeker jobSeeker) {
		
		var result = this.jobSeekerDao.save(jobSeeker);
		if(result != null) {
			return new SuccessResult(Messages.userAddSuccess);
		}
		return new ErrorResult();
	}

	@Override
	public Result validate(JobSeeker jobSeeker) {
		var errors = validationService.validate(jobSeeker);
		
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
	public Result checkLogin(String email, String password) {
		JobSeeker jobSeeker = this.jobSeekerDao.findUserByEmailAndPassword(email, password);
			if(jobSeeker != null) {
				if(jobSeeker.isActive() && !jobSeeker.isDeleted()) {
					return new SuccessResult();
				}
			}
	        return new ErrorResult();
		
	}

	@Override
	public boolean checkIfExistsUserByNationalityId(String nationalityId) {
		return jobSeekerDao.existsByNationalityId(nationalityId);
	}

}