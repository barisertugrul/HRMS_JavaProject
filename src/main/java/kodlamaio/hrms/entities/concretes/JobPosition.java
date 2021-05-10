package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="job_positions")
@Data
public class JobPosition {
	
	@Id
	@GeneratedValue
	@Column(name="position_id")
	private int id;
	
	@Column(name="position_name")
	private String name;
	
	@Column(name="position_description")
	private String description;
	

	public JobPosition() {}
	
	public JobPosition(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
}
