package kodlamaio.hrms.business.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ActivationService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;

@Service
public class EmailActivationManager  implements ActivationService {

	@Override
	public Result sendActivationCode(String email, String activationCode) {
		// TODO Email adresine aktivasyon kodunun gönderilmesi
		/*
		 * CreateActivation link: link güvenlik için hashlenmeli ve
		 * parse edildiğinde data kısmından email ve
		 * aktivasyon kodu alınabilmeli
		 */
		
		String activationLink = "(AuthContrellerLink)/activate?email=" + email + "&activationcode=" + activationCode;

		System.out.println("Your activation code:  " + activationCode );
		System.out.println("Please click the activation link to activate your account:  " + activationLink );
		return new SuccessResult();
	}


}