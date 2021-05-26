package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.abstracts.ValidatableUser;

public interface CheckRealPersonService {
	boolean validate(ValidatableUser user);
}
