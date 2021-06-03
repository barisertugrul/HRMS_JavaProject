package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.dtos.CvDto;

public interface CvDao extends JpaRepository<Cv, Integer> {
	//Cv getById(int cvId);
	
	  @Query(value = "Select new kodlamaio.hrms.entities.dtos.CvDto" +
	  "(cv.id, cv.githubLink, cv.linkedinLink, cv.summary, cv.candidate, cv.languages, cv.educations, cv.experiences, cv.skills, cv.photos)"
	  + " From Cv cv"
	  + " LEFT JOIN (Select cv.educations From cv.educations Order By graduationDate Nulls First Desc) ed"
	  + " LEFT JOIN cv.languages ln"
	  + " LEFT JOIN (Select cv.experiences From cv.experiences ORDER BY departureDate NULLS FIRST DESC) ex"
	  + " LEFT JOIN cv.skills sk"
	  + " LEFT JOIN cv.photos ph"
	  + " LEFT JOIN cv.candidate ca"
	  + " where cv.id=:cvId", nativeQuery = true)
	 List<CvDto> getByIdWithOrdered(int cvId);
	  
	/*
	 * @Query("Select new kodlamaio.hrms.entities.dtos.CvDto" +
	 * "(cv.id, cv.githubLink, cv.linkedinLink, cv.summary, cv.candidate, cv.languages, cv.educations, cv.experiences, cv.skills, cv.photos)"
	 * + " From Cv cv" + " LEFT JOIN cv.candidate ca" +
	 * " LEFT JOIN cv.educations ed" + " LEFT JOIN cv.languages ln" +
	 * " LEFT JOIN cv.experiences ex" + " LEFT JOIN cv.skills sk" +
	 * " LEFT JOIN cv.photos ph" + " where cv.id=:cvId")
	 */
	
}
