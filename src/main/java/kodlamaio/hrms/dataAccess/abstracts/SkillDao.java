package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Skill;

public interface SkillDao extends JpaRepository<Skill, Integer> {

}
