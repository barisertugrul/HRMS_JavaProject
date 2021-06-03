package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

	private JobAdvertisementDao jobAdvertisementDao;
	private EmployerService employerService;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, EmployerService employerService) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.employerService = employerService;
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		if(employerService.isActive(jobAdvertisement.getEmployer().getId())) {
			var result = this.jobAdvertisementDao.save(jobAdvertisement);
			if(result != null) {
				return new SuccessResult(Messages.advertiseAddedSuccessfully);
			}
			return new ErrorResult();
		}
		return new ErrorResult(Messages.employerNotActive);
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>
		(this.jobAdvertisementDao.findAll(),Messages.listedData);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisements() {
		
		return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.getActiveAdvertisements(), Messages.listedData);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByCreatedDate(LocalDate date) {
		return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.getActiveAdvertisementsByCreatedDate(date), Messages.listedData);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByExpirationDate(LocalDate date) {
		return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.getActiveAdvertisementsByExpirationDate(date), Messages.listedData);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobAdvertisementDto>>(this.jobAdvertisementDao.getActiveAdvertisementsByEmployerId(employerId), Messages.listedData);
	}

	@Override
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsSortBy(String fieldName, boolean isDesc) {
		var direction = (isDesc)? Sort.Direction.DESC : Sort.Direction.ASC;
		Sort sort = Sort.by(direction,fieldName);
		return new SuccessDataResult<List<JobAdvertisementDto>>
		(this.jobAdvertisementDao.getActiveAdvertisements(sort));
	}

	@Override
	public Result setAdvertisementStatus(int advertisementId, boolean status) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getByAdvertisementId(advertisementId);
		jobAdvertisement.setActive(status);
		var result = this.jobAdvertisementDao.save(jobAdvertisement);
		if(result != null) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}

}
