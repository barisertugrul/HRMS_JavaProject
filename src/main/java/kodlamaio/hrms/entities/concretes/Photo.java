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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="photos")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cv"})
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="photo_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="image")
	private String image;
	
	@Column(name="created_date", columnDefinition = "Date defult CURRENT_DATE")
	private LocalDate createdDate = LocalDate.now();

	@JoinColumn(name = "cv_id", insertable = false, updatable = false)
	@ManyToOne(targetEntity = Cv.class, fetch = FetchType.EAGER)
	@JsonIgnore
	private Cv cv;
	
	@Column(name = "cv_id")
	private int cvId;
	
	public Photo(String title, String image) {
		super();
		this.title = title;
		this.image = image;
	}

	/*
	 * public StoredFile getUpload() { StoredFile file = new StoredFile();
	 * file.setPreloadedFile(image); return file; }
	 * 
	 * public void setUpload(StoredFile file) { this.image =
	 * file.getPreloadedFile(); }
	 */
}
