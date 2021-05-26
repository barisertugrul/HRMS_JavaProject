package kodlamaio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserValidationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.business.constants.Messages;

@Service
public class JobSeekerValidationManager implements UserValidationService<JobSeeker> {

	private List<String> errors = new ArrayList<String>();
	
	@Override
	public DataResult<List<String>> validate(JobSeeker jobSeeker) {
		errors.clear();
		if(checkIsNotNull("Email", jobSeeker.getEmail()).isSuccess()) {
			checkEmailPattern(jobSeeker.getEmail());
		}
		checkIsNotNull("First Name", jobSeeker.getFirstName());
		checkIsNotNull("Last Name", jobSeeker.getLastName());
		if(checkIsNotNull("Nationality Id", jobSeeker.getNationalityId()).isSuccess()) {
			isTCKN(jobSeeker.getNationalityId());
		}
		if(checkIsNotNull("Year of Birth Date", String.valueOf(jobSeeker.getYearOfBirth())).isSuccess()) {
			isYear(jobSeeker.getYearOfBirth());
		}
		if(!this.errors.isEmpty()) {
			return new ErrorDataResult<List<String>>(errors);
		}
		return null;
	}
	
	private Result checkIsNotNull(String fieldName, String value) {
		if (value == null || value.isBlank()) {
			errors.add(fieldName + Messages.canNotNull);
			return new ErrorResult();
		}
		return new SuccessResult();
	}
	
	private Result isYear(int value) {
		Pattern pattern = Pattern.compile("^(19|20)\\d{2}$");
		boolean result = pattern.matcher(String.valueOf(value)).matches();
		if (!result) {
			errors.add(Messages.invalidYearOfBirth);
			return new ErrorResult();
		}
		return new SuccessResult();
	}

	private Result checkEmailPattern(String email) {
		
		Pattern pattern = Pattern.compile(
				"^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
		boolean result = pattern.matcher(email).matches();
		if (!result) {
			errors.add(Messages.invalidEmail);
			return new ErrorResult();
		}
		return new SuccessResult();
	}
	
	private Result isTCKN(String tckn) {
		
        Pattern pattern = Pattern.compile("^[1-9]{1}[0-9]{9}[02468]{1}$");
		boolean result = pattern.matcher(tckn).matches();
		if (!result) {
			errors.add(Messages.invalidTCKNo);
			return new ErrorResult();
		}
		return new SuccessResult();
    }

}
