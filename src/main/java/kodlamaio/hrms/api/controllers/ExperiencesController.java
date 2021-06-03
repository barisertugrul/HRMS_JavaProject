package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Experience;

@RestController
@RequestMapping("/api/experiences")
public class ExperiencesController {
	private ExperienceService experienceService;
	
	@Autowired
	public ExperiencesController(ExperienceService experienceService) {
		super();
		this.experienceService = experienceService;
	}

	@GetMapping("/getOrderedByCvId")
	DataResult<List<Experience>> getOrderedByCvId(int cvId){
		return experienceService.getByCvIdWithOrdered(cvId);
	}

	@GetMapping("/getOrderedByCandidateId")
	DataResult<List<Experience>> getOrderedByCandidateId(int candidateId){
		return experienceService.getByCandidateIdWithOrdered(candidateId);
	}
}
