package kodlamaio.hrms.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

public interface JobAdvertisementService {
	Result add(JobAdvertisement jobAdvertisement);
	Result setAdvertisementStatus(int advertisementId, boolean status);
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisementDto>> getActiveAdvertisements();
	DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsSortBy(String fieldName, boolean isDesc);
	DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByCreatedDate(LocalDate date);
	DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByExpirationDate(LocalDate date);
	DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByEmployerId(int employerId);
}
