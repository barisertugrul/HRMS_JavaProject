package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.Skill;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {


	private SkillService skillService;

	public SkillsController(SkillService skillService) {
		super();
		this.skillService = skillService;
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Skill skill) {
		return ResponseEntity.ok(this.skillService.add(skill));
	}

	@PostMapping("/getById")
	public DataResult<Skill> getById(int skillId){
		return skillService.getById(skillId);
	}

	@PostMapping("/getByCvId")
	public DataResult<List<Skill>> getByCvId(int cvId){
		return skillService.getByCvId(cvId);
	}

	@PostMapping("/getByCandidateId")
	public DataResult<List<Skill>> getByCandidateId(int candidateId){
		return skillService.getByCandidateId(candidateId);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
	}
}
