package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ExperienceDao;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Experience;

@Service
public class ExperienceManager implements ExperienceService {

	private ExperienceDao experienceDao;
	
	@Autowired
	public ExperienceManager(ExperienceDao experienceDao) {
		super();
		this.experienceDao = experienceDao;
	}

	@Override
	public Result add(Experience experience) {
		this.experienceDao.save(experience);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Experience>> getByCvIdWithOrdered(int cvId) {
		return new SuccessDataResult<List<Experience>>(this.experienceDao.getByCvIdWithOrdered(cvId));
	}

	@Override
	public DataResult<List<Experience>> getByCandidateIdWithOrdered(int candidateId) {
		return new SuccessDataResult<List<Experience>>(this.experienceDao.getByCandidateIdWithOrdered(candidateId));
	}

}
