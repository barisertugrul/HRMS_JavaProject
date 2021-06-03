package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Experience;

public interface ExperienceService {

	Result add(Experience experience);
	DataResult<List<Experience>> getByCvIdWithOrdered(int cvId);
	DataResult<List<Experience>> getByCandidateIdWithOrdered(int candidateId);
}
