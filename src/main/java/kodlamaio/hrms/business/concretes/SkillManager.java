package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.SkillDao;
import kodlamaio.hrms.entities.concretes.Skill;

@Service
public class SkillManager implements SkillService {

	private SkillDao skillDao;
	
	@Autowired
	public SkillManager(SkillDao skillDao) {
		super();
		this.skillDao = skillDao;
	}

	@Override
	public Result add(Skill skill) {
		this.skillDao.save(skill);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Skill>> getByCvId(int cvId) {
		return new SuccessDataResult<List<Skill>>(this.skillDao.getByCvId(cvId));
	}

	@Override
	public DataResult<Skill> getById(int skillId) {
		return new SuccessDataResult<Skill>(this.skillDao.getById(skillId));
	}

	@Override
	public DataResult<List<Skill>> getByCandidateId(int candidateId) {
		return new SuccessDataResult<List<Skill>>(this.skillDao.getByCandidateId(candidateId));
	}

}
