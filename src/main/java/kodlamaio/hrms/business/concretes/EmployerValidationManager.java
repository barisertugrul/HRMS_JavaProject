package kodlamaio.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.constants.Messages;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.business.abstracts.UserValidationService;

@Service
public class EmployerValidationManager implements UserValidationService<Employer> {
private List<String> errors = new ArrayList<String>();
	
	@Override
	public DataResult<List<String>> validate(Employer employer) {
		errors.clear();
		var isEmailNotNull = checkIsNotNull("Email", employer.getEmail()).isSuccess();
		if(isEmailNotNull) {
			checkEmailPattern(employer.getEmail());
		}
		checkIsNotNull("Company Name", employer.getCompanyName());
		checkIsNotNull("Phone Number", employer.getPhoneNumber());
		var isWebsiteNotNull = checkIsNotNull("Website", employer.getWebsite()).isSuccess();
		
		if(isWebsiteNotNull && isEmailNotNull) {
			checkEmailDomain(employer.getEmail(), employer.getWebsite());
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
	
	private Result checkEmailDomain(String email, String website) {
		Pattern regex = Pattern.compile("(?<=@)\\S+");
		Matcher regexMatcher = regex.matcher(email);
		if (regexMatcher.find()) {
		 String resultString = regexMatcher.group();

			if (resultString.equals(website)) {
				System.out.println(resultString);
				return new SuccessResult();
			}
		}
		errors.add(Messages.notMatchEmailAndDomain);
		return new ErrorResult();
	}
}
