package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.CandidateForRegisterDto;
import kodlamaio.hrms.entities.dtos.UserForLoginDto;

public interface AuthService {

	Result login(UserForLoginDto userForLoginDto);
	Result loginEmployee(UserForLoginDto userForLoginDto);
	Result loginCandidate(UserForLoginDto userForLoginDto);
	Result loginEmployer(UserForLoginDto userForLoginDto);
    Result employerRegister(EmployerForRegisterDto employerForRegisterDto);
    Result candidateRegister(CandidateForRegisterDto candidateForRegisterDto);

	Result confirmActivation(String email, String activationCode);
}
