package kodlamaio.hrms.business.adapters;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CheckRealPersonService;
import kodlamaio.hrms.entities.abstracts.ValidatableUser;

@Service
public class MernisServiceAdapter implements CheckRealPersonService {


	@Override
	public boolean validate(ValidatableUser user) {
		// TODO Use mernis validation service with KPSPublicSoap
		boolean fakeResult = true;
		return fakeResult;
	}

}
