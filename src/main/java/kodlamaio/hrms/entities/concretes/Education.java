package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="educations")
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class Education {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="education_id")
	private int id;
	
	@Column(name="school_name", nullable=false)
	private String schoolName;
	
	@Column(name="program_name", nullable=false)
	private String programName;
	
	@Column(name="start_date", nullable=false)
	private LocalDate startDate;
	
	@Column(name="graduation_date")
	private LocalDate graduationDate;

	@ManyToOne(targetEntity = Cv.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "cv_id", insertable = false, updatable = false)
	@JsonIgnore
	private Cv cv;
	
	
	@JoinColumn(name = "cv_id", insertable = false, updatable = false)
	@Column(name="cv_id")
	private int cvId;
	
}
