package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface ActivationService {
	Result sendActivationCode(String sendTo, String activationCode);
}
