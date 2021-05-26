package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer>{

	JobSeeker findUserByEmailAndPassword(String email, String password);
	boolean existsByNationalityId(String nationalityId);
}