package kodlamaio.hrms.business.concretes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationService;
import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.business.abstracts.CheckRealPersonService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.CandidateForRegisterDto;
import kodlamaio.hrms.entities.dtos.UserForLoginDto;
import kodlamaio.hrms.business.constants.Messages;

@Service
public class AuthManager implements AuthService {

	private UserService userService;
	private EmployerService employerService;
	private CandidateService candidateService;
	private CheckRealPersonService checkRealPersonService;
	private ActivationService activationService;
	
	@Autowired
	public AuthManager(UserService userService,
			EmployerService employerService,
			CandidateService candidateService,
			CheckRealPersonService checkRealPersonService,
			ActivationService activationService) {
		super();
		this.userService = userService;
		this.employerService = employerService;
		this.candidateService = candidateService;
		this.checkRealPersonService = checkRealPersonService;
		this.activationService = activationService;
		
	}
	
	@Override
	public Result login(UserForLoginDto userForLoginDto) {
		return userService.checkLogin(userForLoginDto.getEmail(), userForLoginDto.getPassword());
	}
	
	@Override
	public Result loginCandidate(UserForLoginDto userForLoginDto) {
		return candidateService.checkLogin(userForLoginDto.getEmail(), userForLoginDto.getPassword());
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
	public Result candidateRegister(CandidateForRegisterDto candidateForRegisterDto) {
		int year = (candidateForRegisterDto.getYearOfBirth() == null)? 0 : candidateForRegisterDto.getYearOfBirth();
		Candidate candidate = new Candidate(
						candidateForRegisterDto.getFirstName(),
						candidateForRegisterDto.getLastName(),
						candidateForRegisterDto.getNationalityId(),
						year,
						candidateForRegisterDto.getEmail(),
						candidateForRegisterDto.getPassword()
						);
		
		var validateResult = validateCandidate(candidate);
		if(!validateResult.isSuccess()) {
			return new ErrorResult(validateResult.getMessage());
		}
		
		if(!isPasswordMatch(candidateForRegisterDto.getPassword(),candidateForRegisterDto.getRepassword())) {
			return new ErrorResult(Messages.passwordsNotMatch);
		}
		
		if(userExists(candidate.getEmail()) || nationalityIdExists(candidate.getNationalityId())) {
			return new ErrorResult(Messages.userExists);
		}
		
		if(!checkIfRealPerson(candidateForRegisterDto)) {
			return new ErrorResult(Messages.NotRealPerson);
		}
		
		candidate.setActivationCode(createActivationCode(candidate.getEmail()));
		
		var resultAdd =  candidateService.add(candidate);
		if(resultAdd.isSuccess()) {
			var resultSend = activationService.sendActivationCode(candidate.getEmail(), candidate.getActivationCode()).isSuccess();
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
		return candidateService.checkIfExistsUserByNationalityId(nationalityId);
	}
	
	private Result validateCandidate(Candidate candidate) {

		return candidateService.validate(candidate);
		
	}
	
	private Result validateEmployer(Employer employer) {

		return employerService.validate(employer);
		
	}
	
	private boolean isPasswordMatch(String password, String repassword) {
		return password.equals(repassword);
	}
	
	private boolean checkIfRealPerson(CandidateForRegisterDto candidate) {

		return checkRealPersonService.validate(candidate);
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
