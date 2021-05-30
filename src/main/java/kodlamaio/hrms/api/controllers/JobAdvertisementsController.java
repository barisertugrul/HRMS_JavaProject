package kodlamaio.hrms.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDto;

@RestController
@RequestMapping("/api/jobadvertisements")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.add(jobAdvertisement);
	}

	@PostMapping("/setAdvertisementStatus")
	public Result setAdvertisementStatus(@RequestParam int jobAdvertisementId, boolean status) {
		return this.jobAdvertisementService.setAdvertisementStatus(jobAdvertisementId, status);
	}

	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll() {
		return this.jobAdvertisementService.getAll();
	}

	@GetMapping("/getActiveAdvertisements")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisements() {
		return this.jobAdvertisementService.getActiveAdvertisements();
	}

	@GetMapping("/getActiveAdvertisementsByCreatedDate")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByCreatedDate(LocalDate date) {
		return this.jobAdvertisementService.getActiveAdvertisementsByCreatedDate(date);
	}

	@GetMapping("/getActiveAdvertisementsByExpirationDate")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByExpirationDate(LocalDate date) {
		return this.jobAdvertisementService.getActiveAdvertisementsByExpirationDate(date);
	}

	@GetMapping("/getActiveAdvertisementsByEmployerId")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsByEmployerId(int employerId) {
		return this.jobAdvertisementService.getActiveAdvertisementsByEmployerId(employerId);
	}

	@GetMapping("/getActiveAdvertisementsSortByCompany")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsSortByCompany(boolean isDesc) {
		return this.jobAdvertisementService.getActiveAdvertisementsSortBy("employer.companyName", isDesc);
	}

	@GetMapping("/getActiveAdvertisementsSortByCreatedDate")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsSortByCreatedDate(boolean isDesc) {
		return this.jobAdvertisementService.getActiveAdvertisementsSortBy("createdDate", isDesc);
	}

	@GetMapping("/getActiveAdvertisementsSortByExpirationDate")
	public DataResult<List<JobAdvertisementDto>> getActiveAdvertisementsSortByExpirationDate(boolean isDesc) {
		return this.jobAdvertisementService.getActiveAdvertisementsSortBy("expirationDate", isDesc);
	}
}
