package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.concretes.Photo;

public interface CvService {
	Result add(Cv cv);
	DataResult<List<Cv>> getAll();
	DataResult<List<Cv>> getByCandidateId(int candidateId);
	DataResult<Photo> uploadCvPhoto(int cvId, MultipartFile photo);
}
