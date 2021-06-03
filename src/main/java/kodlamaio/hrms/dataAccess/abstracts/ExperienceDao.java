package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Experience;

public interface ExperienceDao extends JpaRepository<Experience, Integer> {
	
	@Query("From Experience where cvId=:cvId Order By departureDate DESC Nulls First")
	List<Experience> getByCvIdWithOrdered(int cvId);
	
	@Query(value = "SELECT e.experience_id, e.cv_id, e.workplace_name, e.job_title, e.start_date, e.departure_date"
			+ " From experiences e left join cvs cv on e.cv_id = cv.cv_id"
			+ " where cv.user_id=:candidateId Order By e.departure_date DESC Nulls First", nativeQuery = true)
	List<Experience> getByCandidateIdWithOrdered(int candidateId);

	Experience getById(int experienceId);
}
