package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.Cv;

public interface CvDao extends JpaRepository<Cv, Integer> {
	
List<Cv> getByUserId(int candidateId);
	
}
