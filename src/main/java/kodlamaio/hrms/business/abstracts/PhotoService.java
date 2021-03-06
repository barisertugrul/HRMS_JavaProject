package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Photo;

public interface PhotoService {
	Result add(Photo photo);
	DataResult<List<Photo>> getByCvId(int cvId);
	DataResult<Photo> upload(MultipartFile photo, int cvId);
	DataResult<List<Photo>> getByCandidateId(int candidateId);
	DataResult<Photo> getById(int photoId);
}
