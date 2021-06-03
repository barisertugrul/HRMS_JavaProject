package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer> {
	
	@Query("From Education where cvId=:cvId Order By graduationDate DESC Nulls First")
	List<Education> getByCvIdWithOrdered(int cvId);
	
	@Query(value = "SELECT e.education_id, e.cv_id, e.school_name, e.program_name, e.start_date, e.graduation_date"
			+ " From educations e left join cvs cv on e.cv_id = cv.cv_id"
			+ " where cv.user_id=:candidateId Order By e.graduation_date DESC Nulls First", nativeQuery = true)
	List<Education> getByCandidateIdWithOrdered(int candidateId);

	Education getById(int educationId);
}
