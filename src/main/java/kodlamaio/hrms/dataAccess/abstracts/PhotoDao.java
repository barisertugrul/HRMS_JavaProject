package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.hrms.entities.concretes.Photo;

public interface PhotoDao extends JpaRepository<Photo, Integer> {

	List<Photo> getByCvId(int cvId);
	Photo getById(int photoId);
	@Query(value = "SELECT p.photo_id, p.cv_id, p.image, p.title"
			+ " From photos p left join cvs cv on p.cv_id = cv.cv_id"
			+ " where cv.user_id=:candidateId", nativeQuery = true)
	List<Photo> getByCandidateId(int candidateId);

}
