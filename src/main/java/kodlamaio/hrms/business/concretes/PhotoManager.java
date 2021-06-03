package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.helpers.PhotoUploadHelper;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.PhotoDao;
import kodlamaio.hrms.entities.concretes.Photo;

@Service
public class PhotoManager implements PhotoService {

	PhotoUploadHelper photoUploadHelper;
	PhotoDao photoDao;
	
	@Autowired
	public PhotoManager(PhotoDao photoDao, PhotoUploadHelper photoUploadHelper) {
		super();
		this.photoDao = photoDao;
		this.photoUploadHelper = photoUploadHelper;
	}

	@Override
	public Result add(Photo photo) {
		if(this.photoDao.save(photo) != null) {
			return new SuccessResult();
		}
		return new ErrorResult();
	}

	@Override
	public DataResult<List<Photo>> getByCvId(int cvId) {
		var result = this.photoDao.getByCvId(cvId);
		if(result != null) {
			return new SuccessDataResult<List<Photo>>(result);
		}
		return new ErrorDataResult<List<Photo>>();
	}

	@Override
	public DataResult<Photo> upload(MultipartFile photoFile, int cvId) {
		DataResult<Map<String, String>> result = this.photoUploadHelper.upload(photoFile);

		if(result.isSuccess()) {

			Photo photo = new Photo();
			photo.setCvId(cvId);
			photo.setCreatedDate(LocalDate.now());
			photo.setImage(result.getData().get("url"));
			photo.setTitle(result.getData().get("public_id"));
			Result addingResult = this.add(photo);
			if (addingResult.isSuccess()) {
				return new SuccessDataResult<Photo>(photo);
			}
		}
		return new ErrorDataResult<Photo>(null,"Dosya yüklenemedi");
	}

	@Override
	public DataResult<List<Photo>> getByCandidateId(int candidateId) {
		var result =  this.photoDao.getByCandidateId(candidateId);
		if(result != null) {
			return new SuccessDataResult<List<Photo>>(result);
		}
		return new ErrorDataResult<List<Photo>>();
	}

	@Override
	public DataResult<Photo> getById(int photoId) {
		var result =  this.photoDao.getById(photoId);
		if(result != null) {
			return new SuccessDataResult<Photo>(result);
		}
		return new ErrorDataResult<Photo>();
	}

}
