package kodlamaio.hrms.business.concretes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationService;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.CheckRealPersonService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.JobSeekerService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;
import kodlamaio.hrms.entities.dtos.UserForLoginDto;
import kodlamaio.hrms.business.constants.Messages;

@Service
public class AuthManager implements AuthService {

	private UserService userService;
	private EmployerService employerService;
	private JobSeekerService jobSeekerService;
	private CheckRealPersonService checkRealPersonService;
	private ActivationService activationService;
	
	@Autowired
	public AuthManager(UserService userService,
			EmployerService employerService,
			JobSeekerService jobSeekerService,
			CheckRealPersonService checkRealPersonService,
			ActivationService activationService) {
		super();
		this.userService = userService;
		this.employerService = employerService;
		this.jobSeekerService = jobSeekerService;
		this.checkRealPersonService = checkRealPersonService;
		this.activationService = activationService;
		
	}
	
	@Override
	public Result login(UserForLoginDto userForLoginDto) {
		return userService.checkLogin(userForLoginDto.getEmail(), userForLoginDto.getPassword());
	}
	
	@Override
	public Result loginJobSeeker(UserForLoginDto userForLoginDto) {
		return jobSeekerService.checkLogin(userForLoginDto.getEmail(), userForLoginDto.getPassword());
	}

	@Override
	public Result loginEmployee(UserForLoginDto userForLoginDto) {
		//TODO Coding login for Employee
		//return employeeService.checkLogin(userForLoginDto.getEmail(), userForLoginDto.getPassword());;
		return null;
	}

	@Override
	public Result loginEmployer(UserForLoginDto userForLoginDto) {
		return employerService.checkLogin(userForLoginDto.getEmail(), userForLoginDto.getPassword());
	}

	@Override
	public Result employerRegister(EmployerForRegisterDto employerForRegisterDto) {

		Employer employer = new Employer();
		employer.setEmail(employerForRegisterDto.getEmail());
		employer.setCompanyName(employerForRegisterDto.getCompanyName());
		employer.setWebsite(employerForRegisterDto.getWebsite());
		employer.setPhoneNumber(employerForRegisterDto.getPhoneNumber());
		employer.setPassword(employerForRegisterDto.getPassword());
		var validateResult = validateEmployer(employer);
		if(!validateResult.isSuccess()) {
			return new ErrorResult(validateResult.getMessage());
		}
		
		if(userExists(employer.getEmail())) {
			return new ErrorResult(Messages.userExists);
		}
		

		employer.setActivationCode(createActivationCode(employer.getEmail()));
		
		var resultAdd =  this.employerService.add(employer);
		if(resultAdd.isSuccess()) {
			var resultSend = activationService.sendActivationCode(employer.getEmail(), employer.getActivationCode()).isSuccess();
			if(resultSend) {
				return new SuccessResult(Messages.saveUserAndSendCode);
			}else {
				return new SuccessResult(Messages.saveUserButNotSendCode);
			}
		}
		return new ErrorResult(resultAdd.getMessage());
	}

	@Override
	public Result jobSeekerRegister(JobSeekerForRegisterDto jobSeekerForRegisterDto) {
		int year = (jobSeekerForRegisterDto.getYearOfBirth() == null)? 0 : jobSeekerForRegisterDto.getYearOfBirth();
		JobSeeker jobSeeker = new JobSeeker(
						jobSeekerForRegisterDto.getFirstName(),
						jobSeekerForRegisterDto.getLastName(),
						jobSeekerForRegisterDto.getNationalityId(),
						year,
						jobSeekerForRegisterDto.getEmail(),
						jobSeekerForRegisterDto.getPassword()
						);
		
		var validateResult = validateJobSeeker(jobSeeker);
		if(!validateResult.isSuccess()) {
			return new ErrorResult(validateResult.getMessage());
		}
		
		if(!isPasswordMatch(jobSeekerForRegisterDto.getPassword(),jobSeekerForRegisterDto.getRepassword())) {
			return new ErrorResult(Messages.passwordsNotMatch);
		}
		
		if(userExists(jobSeeker.getEmail()) || nationalityIdExists(jobSeeker.getNationalityId())) {
			return new ErrorResult(Messages.userExists);
		}
		
		if(!checkIfRealPerson(jobSeekerForRegisterDto)) {
			return new ErrorResult(Messages.NotRealPerson);
		}
		
		jobSeeker.setActivationCode(createActivationCode(jobSeeker.getEmail()));
		
		var resultAdd =  jobSeekerService.add(jobSeeker);
		if(resultAdd.isSuccess()) {
			var resultSend = activationService.sendActivationCode(jobSeeker.getEmail(), jobSeeker.getActivationCode()).isSuccess();
			if(resultSend) {
				return new SuccessResult(Messages.saveUserAndSendCode);
			}else {
				return new SuccessResult(Messages.saveUserButNotSendCode);
			}
		}
		return new ErrorResult(resultAdd.getMessage());
	}


	private boolean userExists(String email) {
		return userService.checkIfExistsUserByEmail(email);
	}


	private boolean nationalityIdExists(String nationalityId) {
		return jobSeekerService.checkIfExistsUserByNationalityId(nationalityId);
	}
	
	private Result validateJobSeeker(JobSeeker jobSeeker) {

		return jobSeekerService.validate(jobSeeker);
		
	}
	
	private Result validateEmployer(Employer employer) {

		return employerService.validate(employer);
		
	}
	
	private boolean isPasswordMatch(String password, String repassword) {
		return password.equals(repassword);
	}
	
	private boolean checkIfRealPerson(JobSeekerForRegisterDto jobSeeker) {

		return checkRealPersonService.validate(jobSeeker);
	}

	private String createActivationCode(String email) {
		// TODO Hashing with Email
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	@Override
	public Result confirmActivation(String email, String activationCode) {
		return userService.confirmActivation(email, activationCode);
	}

}
