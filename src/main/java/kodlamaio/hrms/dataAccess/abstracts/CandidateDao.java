package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer>{

	Candidate findCandidateByEmailAndPassword(String email, String password);
	boolean existsByNationalityId(String nationalityId);
}