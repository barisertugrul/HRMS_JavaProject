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
@Entity
@Table(name="skills")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="skill_id")
	private int id;
	
	@Column(name="skill", nullable=false)
	private String skill;
	
	@Column(name="skill_level")
	private int skillLevel;

	@JoinColumn(name = "cv_id", insertable = false, updatable = false)
	@ManyToOne(targetEntity = Cv.class, fetch = FetchType.EAGER)
	@JsonIgnore
	private Cv cv;
	
	@Column(name = "cv_id")
	private int cvId;
}
