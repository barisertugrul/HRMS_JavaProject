package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cv_languages") 
@Entity
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
//@JsonIgnoreProperties(ignoreUnknown = true, 
//value = {"hibernateLazyInitializer","handler","cv"})
public class CvLanguage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="language_id")
	private int id;
	
	@Column(name="language_name", nullable=false)
	private String languageName;
	
	@Column(name="language_level", nullable=false)
	private int languageLevel;

	@JoinColumn(name = "cv_id", insertable = false, updatable = false)
	@ManyToOne(targetEntity = Cv.class, fetch = FetchType.EAGER)
	@JsonIgnore
	private Cv cv;
	
	@Column(name = "cv_id")
	private int cvId;
	
}
