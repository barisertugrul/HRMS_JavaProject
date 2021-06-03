package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.CvLanguage;

@RestController
@RequestMapping("/api/cvlanguages")
public class CvLanguagesController {

	private CvLanguageService languageService;

	@Autowired
	public CvLanguagesController(CvLanguageService languageService) {
		super();
		this.languageService = languageService;
	}


	@PostMapping("/getById")
	public DataResult<CvLanguage> getById(int languageId){
		return languageService.getById(languageId);
	}

	@PostMapping("/getByCvId")
	public DataResult<List<CvLanguage>> getByCvId(int cvId){
		return languageService.getByCvId(cvId);
	}

	@PostMapping("/getByCandidateId")
	public DataResult<List<CvLanguage>> getByCandidateId(int candidateId){
		return languageService.getByCandidateId(candidateId);
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
