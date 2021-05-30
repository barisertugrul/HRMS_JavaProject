package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserValidationService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private UserValidationService<Candidate> validationService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, UserValidationService<Candidate> validationService) {
		super();
		this.candidateDao = candidateDao;
		this.validationService = validationService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>
		(this.candidateDao.findAll());	
	}

	@Override
	public Result add(Candidate candidate) {
		
		var result = this.candidateDao.save(candidate);
		if(result != null) {
			return new SuccessResult(Messages.userAddedSuccess);
		}
		return new ErrorResult();
	}

	@Override
	public Result validate(Candidate candidate) {
		var errors = validationService.validate(candidate);
		
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
		Candidate candidate = this.candidateDao.findCandidateByEmailAndPassword(email, password);
			if(candidate != null) {
				if(candidate.isActive() && !candidate.isDeleted()) {
					return new SuccessResult();
				}
			}
	        return new ErrorResult();
		
	}

	@Override
	public boolean checkIfExistsUserByNationalityId(String nationalityId) {
		return candidateDao.existsByNationalityId(nationalityId);
	}

}