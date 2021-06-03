package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="cvs")
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler", 
//	"languages", "educations", "experiences", "skills", "photos"})
@PrimaryKeyJoinColumn(name="user_id",referencedColumnName = "user_id")
public class Cv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="cv_id")
	private int id;
	
	@Column(name="github_link")
	private String githubLink;
	
	@Column(name="linkedin_link")
	private String linkedinLink;
	
	@Column(name="summary")
	private String summary;
	
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@ManyToOne(targetEntity = Candidate.class, fetch = FetchType.EAGER)
	@JsonIgnore
	private Candidate candidate;
	
	@Column(name = "user_id")
	private int userId;
	

	@OneToMany(targetEntity=CvLanguage.class, mappedBy="cv",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CvLanguage> languages;
	
	@OneToMany(targetEntity=Education.class, mappedBy="cv",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Education> educations;
	
	@OneToMany(targetEntity=Experience.class, mappedBy="cv",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Experience> experiences;
	
	@OneToMany(targetEntity=Skill.class, mappedBy="cv",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Skill> skills;
	
	@OneToMany(targetEntity=Photo.class, mappedBy="cv",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Photo> photos;
}
