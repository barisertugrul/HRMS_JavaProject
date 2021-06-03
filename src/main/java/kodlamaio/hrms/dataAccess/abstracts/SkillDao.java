package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Skill;

public interface SkillDao extends JpaRepository<Skill, Integer> {

	List<Skill> getByCvId(int cvId);
	Skill getById(int skillId);
	@Query(value = "SELECT s.skill_id, s.cv_id, s.skill, s.skill_level"
			+ " From cv_languages s left join cvs cv on s.cv_id = cv.cv_id"
			+ " where cv.user_id=:candidateId Order By s.skill_name", nativeQuery = true)
	List<Skill> getByCandidateId(int candidateId);
}
