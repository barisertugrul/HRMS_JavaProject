package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Education;

@RestController
@RequestMapping("/api/educations")
public class EducationsController {
	
	private EducationService educationService;
	
	@Autowired
	public EducationsController(EducationService educationService) {
		super();
		this.educationService = educationService;
	}

	@GetMapping("/getOrderedByCvId")
	DataResult<List<Education>> getOrderedByCvId(int cvId){
		return educationService.getByCvIdWithOrdered(cvId);
	}

	@GetMapping("/getOrderedByCandidateId")
	DataResult<List<Education>> getOrderedByCandidateId(int candidateId){
		return educationService.getByCandidateIdWithOrdered(candidateId);
	}
}
