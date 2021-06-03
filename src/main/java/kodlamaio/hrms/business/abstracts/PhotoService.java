package kodlamaio.hrms.business.abstracts;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Photo;

public interface PhotoService {
	Result add(Photo photo);
	DataResult<List<Photo>> getByCvId(int cvId);
	DataResult<Photo> upload(MultipartFile photo, int cvId) throws IOException;
}
