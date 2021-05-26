package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;

public interface UserValidationService<T> {
	
	DataResult<List<String>> validate(T user);
}
