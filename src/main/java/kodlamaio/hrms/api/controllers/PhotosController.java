package kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.entities.concretes.Photo;

@RestController
@RequestMapping("/api/photos")
public class PhotosController {

	private PhotoService photoService;

	@Autowired
	public PhotosController(PhotoService photoService) {
		super();
		this.photoService = photoService;
	}

	@PostMapping("/photoUpload")
	public ResponseEntity<?> photoUpload(@RequestParam("cvId") int cvId, @RequestParam("photo") MultipartFile photo) {
		return ResponseEntity.ok(this.photoService.upload(photo,cvId));
	}

	@PostMapping("/getById")
	public DataResult<Photo> getById(int photoId){
		return photoService.getById(photoId);
	}

	@PostMapping("/getByCvId")
	public DataResult<List<Photo>> getByCvId(int cvId){
		return photoService.getByCvId(cvId);
	}

	@PostMapping("/getByCandidateId")
	public DataResult<List<Photo>> getByCandidateId(int candidateId){
		return photoService.getByCandidateId(candidateId);
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
