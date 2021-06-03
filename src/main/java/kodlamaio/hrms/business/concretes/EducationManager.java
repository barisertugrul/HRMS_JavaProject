package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.entities.concretes.Education;

@Service
public class EducationManager implements EducationService {

	private EducationDao educationDao;

	@Autowired
	public EducationManager(EducationDao educationDao) {
		super();
		this.educationDao = educationDao;
	}
	
	@Override
	public Result add(Education education) {
		this.educationDao.save(education);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Education>> getByCvIdWithOrdered(int cvId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getByCvIdWithOrdered(cvId));
	}

	@Override
	public DataResult<List<Education>> getByCandidateIdWithOrdered(int candidateId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getByCandidateIdWithOrdered(candidateId));
	}

	@Override
	public DataResult<Education> getById(int educationId) {
		return new SuccessDataResult<Education>(this.educationDao.getById(educationId));
	}

}
