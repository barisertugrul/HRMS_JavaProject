package kodlamaio.hrms.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer> {
	
	JobAdvertisement getByAdvertisementId(int advertisementId);
	
	@Query(value = "SELECT new kodlamaio.hrms.entities.dtos.JobAdvertisementDto(ja.advertisementId as advertisementId, ja.employer.companyName as companyName, ja.jobPosition.positionName as positionName,"
			+ " ja.numberOfOpenPositions as numberOfOpenPositions, ja.createdDate as createdDate, ja.expirationDate as expirationDate) FROM JobAdvertisement ja"
			+ " WHERE ja.active = true")
	List<JobAdvertisementDto> getActiveAdvertisements();

	@Query(value = "SELECT new kodlamaio.hrms.entities.dtos.JobAdvertisementDto(ja.advertisementId as advertisementId, ja.employer.companyName as companyName, ja.jobPosition.positionName as positionName,"
			+ " ja.numberOfOpenPositions as numberOfOpenPositions, ja.createdDate as createdDate, ja.expirationDate as expirationDate) FROM JobAdvertisement ja"
			+ " WHERE ja.active = true")
	List<JobAdvertisementDto> getActiveAdvertisements(Sort sort);
	
	@Query(value = "SELECT new kodlamaio.hrms.entities.dtos.JobAdvertisementDto(ja.advertisementId as advertisementId, ja.employer.companyName as companyName, ja.jobPosition.positionName as positionName,"
			+ " ja.numberOfOpenPositions as numberOfOpenPositions, ja.createdDate as createdDate, ja.expirationDate as expirationDate) FROM JobAdvertisement ja"
			+ " WHERE ja.active = true and ja.createdDate =:date")
	List<JobAdvertisementDto> getActiveAdvertisementsByCreatedDate(LocalDate date);
	
	@Query(value = "SELECT new kodlamaio.hrms.entities.dtos.JobAdvertisementDto(ja.advertisementId as advertisementId, ja.employer.companyName as companyName, ja.jobPosition.positionName as positionName,"
			+ " ja.numberOfOpenPositions as numberOfOpenPositions, ja.createdDate as createdDate, ja.expirationDate as expirationDate) FROM JobAdvertisement ja"
			+ " WHERE ja.active = true and ja.expirationDate =:date")
	List<JobAdvertisementDto> getActiveAdvertisementsByExpirationDate(LocalDate date);
	
	@Query(value = "SELECT new kodlamaio.hrms.entities.dtos.JobAdvertisementDto(ja.advertisementId as advertisementId, ja.employer.companyName as companyName, ja.jobPosition.positionName as positionName,"
			+ " ja.numberOfOpenPositions as numberOfOpenPositions, ja.createdDate as createdDate, ja.expirationDate as expirationDate) FROM JobAdvertisement ja"
			+ " WHERE ja.active = true and ja.employer.id =:employerId")
	List<JobAdvertisementDto> getActiveAdvertisementsByEmployerId(int employerId);
}
