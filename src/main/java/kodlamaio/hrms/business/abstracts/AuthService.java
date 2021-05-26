package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;
import kodlamaio.hrms.entities.dtos.UserForLoginDto;

public interface AuthService {

	Result login(UserForLoginDto userForLoginDto);
	Result loginEmployee(UserForLoginDto userForLoginDto);
	Result loginJobSeeker(UserForLoginDto userForLoginDto);
	Result loginEmployer(UserForLoginDto userForLoginDto);
    Result employerRegister(EmployerForRegisterDto employerForRegisterDto);
    Result jobSeekerRegister(JobSeekerForRegisterDto jobSeekerForRegisterDto);

	boolean comfirmActivation(String email, String activationCode);
}
