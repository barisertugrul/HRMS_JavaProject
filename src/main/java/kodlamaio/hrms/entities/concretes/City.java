package kodlamaio.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cities")
//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisements"})
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="city_id")
	private int cityId;
	
	@Column(name="city_name", nullable=false)
	private String cityName;
	
	@OneToMany(mappedBy = "city")
	private List<JobAdvertisement> jobAdvertisements;

	public City(String cityName) {
		super();
		this.cityName = cityName;
	}
	
}
