package kodlamaio.hrms.entities.dtos;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.CvLanguage;
import kodlamaio.hrms.entities.concretes.Education;
import kodlamaio.hrms.entities.concretes.Experience;
import kodlamaio.hrms.entities.concretes.Photo;
import kodlamaio.hrms.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true, 
//value ={"hibernateLazyInitializer","handler", 
//	"languages", "educations", "experiences", "skills", "photos"})
public class CvDto {
	
	private int id;
	
	private String githubLink;
	
	private String linkedinLink;
	
	private String summary;

	private Candidate candidate;

	private List<CvLanguage> languages;

	private List<Education> educations;

	private List<Experience> experiences;

	private List<Skill> skills;

	private List<Photo> photos;

}

