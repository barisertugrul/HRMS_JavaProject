package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="job_advertisements")
public class JobAdvertisement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="advertisement_id", nullable=false)
	private int advertisementId;
	
	@Column(name="job_definition", nullable=false)
	private String jobDefinition;

	@Column(name="min_salary", nullable=true)
	private double minSalary;

	@Column(name="max_salary", nullable=true)
	private double maxSalary;

	@Column(name="number_of_open_positions", nullable=false)
	private short numberOfOpenPositions;

	@Column(name="created_date", columnDefinition = "Date defult CURRENT_DATE")
	private LocalDate createdDate = LocalDate.now();

	@Column(name="expiration_date", nullable=true)
	private LocalDate expirationDate;
	
	@Column(name= "is_active", columnDefinition = "boolean default true")
	private boolean active = true;
	
	@ManyToOne()
	@JoinColumn(name="city_id")
	private City city;
	
	@ManyToOne()
	@JoinColumn(name="employer_id")
	private Employer employer;
	
	@ManyToOne()
	@JoinColumn(name="position_id")
	private JobPosition jobPosition;
}
