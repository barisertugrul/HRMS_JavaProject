package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CvLanguageService;
import kodlamaio.hrms.business.abstracts.CvService;
import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.business.abstracts.ExperienceService;
import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.business.abstracts.SkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvDao;
import kodlamaio.hrms.entities.concretes.Cv;
import kodlamaio.hrms.entities.concretes.CvLanguage;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.Photo;
import kodlamaio.hrms.entities.concretes.Skill;

@Service
public class CvManager implements CvService {

	private CvDao cvDao;
	private CvLanguageService cvLanguageService;
	private EducationService educationService;
	private ExperienceService experienceService;
	private SkillService skillService;
	private PhotoService photoService;

	@Autowired
	public CvManager(CvDao cvDao, CvLanguageService cvLanguageService, EducationService educationService,
			ExperienceService experienceService, SkillService skillService, PhotoService photoService) {
		super();
		this.cvDao = cvDao;
		this.cvLanguageService = cvLanguageService;
		this.educationService = educationService;
		this.experienceService = experienceService;
		this.skillService = skillService;
		this.photoService = photoService;
	}

	@Override
	public Result add(Cv cv) {
		var addedCv = this.cvDao.save(cv);
		if (addedCv != null) {

			if (addedCv.getEducations() != null) {
				for (Education education : addedCv.getEducations()) {
					education.setCv(addedCv);
					education.setCvId(addedCv.getId());
					this.educationService.add(education);
				}
			}

			if (addedCv.getExperiences() != null) {
				for (Experience experience : addedCv.getExperiences()) {
					experience.setCv(addedCv);
					experience.setCvId(addedCv.getId());
					this.experienceService.add(experience);
				}
			}

			if (addedCv.getLanguages() != null) {
				for (CvLanguage cvLanguage : addedCv.getLanguages()) {
					cvLanguage.setCv(addedCv);
					cvLanguage.setCvId(addedCv.getId());
					this.cvLanguageService.add(cvLanguage);
				}
			}

			if (addedCv.getSkills() != null) {
				for (Skill skill : addedCv.getSkills()) {
					skill.setCv(cv);
					skill.setCvId(addedCv.getId());
					this.skillService.add(skill);
				}
			}

			if (addedCv.getPhotos() != null) {
				for (Photo photo : addedCv.getPhotos()) {
					photo.setCv(cv);
					photo.setCvId(addedCv.getId());
					this.photoService.add(photo);
				}
			}

			return new SuccessResult("Kay??t ba??ar??ll??");
		}
		return new ErrorResult("Kay??t ba??ar??s??z");
	}

	@Override
	public DataResult<List<Cv>> getAll() {
		return new SuccessDataResult<List<Cv>>(this.cvDao.findAll());
	}

	@Override
	public DataResult<List<Cv>> getByCandidateId(int candidateId) {
		return new SuccessDataResult<List<Cv>>(cvDao.getByUserId(candidateId));
	}

	@Override
	public DataResult<Photo> uploadCvPhoto(int cvId, MultipartFile photoFile) {
		return this.photoService.upload(photoFile, cvId);
	}

}
