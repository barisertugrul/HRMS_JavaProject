package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "candidates")
@PrimaryKeyJoinColumn(name = "user_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
public class Candidate extends User{
	
	@Column(name= "first_name")
	private String firstName;
	
	@Column(name= "last_name")
	private String lastName;
	
	@Column(name= "nationality_id")
	private String nationalityId;
	
	@Column(name= "year_of_birth")
	private int yearOfBirth;

	@JsonIgnore
	@OneToMany(mappedBy = "candidate")
	private List<Cv> cv;
	
	public Candidate(
			String firstName,
			String lastName,
			String nationalityId,
			int yearOfBirth,
			String email,
			String password) {
		
		super(email,password,null);
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalityId = nationalityId;
		this.yearOfBirth = yearOfBirth;
	}

}
