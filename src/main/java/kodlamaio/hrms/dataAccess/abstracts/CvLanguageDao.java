package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.CvLanguage;

public interface CvLanguageDao extends JpaRepository<CvLanguage, Integer> {

	List<CvLanguage> getByCvId(int cvId);
	CvLanguage getById(int languageId);
	@Query(value = "SELECT l.language_id, l.cv_id, l.language_name, l.language_level"
			+ " From cv_languages l left join cvs cv on l.cv_id = cv.cv_id"
			+ " where cv.user_id=:candidateId Order By l.language_name", nativeQuery = true)
	List<CvLanguage> getByCandidateId(int candidateId);
}
