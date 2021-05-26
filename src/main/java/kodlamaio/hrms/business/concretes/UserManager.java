package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.business.constants.Messages;

@Service
public class UserManager implements UserService {

private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>
		(this.userDao.findAll(),"Users listed");
	}

	@Override
	public DataResult<User> getByEmail(String email) {
		
		User user = this.userDao.findUserByEmail(email);
		if(user == null) {
			return new ErrorDataResult<User>();
		}
        return new SuccessDataResult<User>(user);
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("User added successfully");
	}

	@Override
	public Result confirmActivation(String email, String activationCode) {
		User user = this.userDao.findUserByEmail(email);
		if(user != null) {
			if(!user.isActive()) {
				if(user.getActivationCode().equals(activationCode) && !user.isDeleted()) {
					user.setActive(true);
					userDao.save(user);
					return new SuccessResult(Messages.confirmActivationSuccess);
				}
			}
			return new SuccessResult(Messages.allreadyConfirm);
		}
        return new ErrorResult();
	}

	@Override
	public Result checkLogin(String email, String password) {
		User user = this.userDao.findUserByEmailAndPassword(email, password);
		if(user != null) {
			if(user.isActive() && !user.isDeleted()) {
				return new SuccessResult();
			}
		}
        return new ErrorResult();
	}

	@Override
	public boolean checkIfExistsUserByEmail(String email) {
		return this.userDao.existsByEmail(email);
	}

}
