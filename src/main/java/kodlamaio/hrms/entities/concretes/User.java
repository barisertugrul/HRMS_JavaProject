package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name= "id")
	private int id;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "password")
	private String password;
	
	@Column(name= "register_date", columnDefinition = "Date defult CURRENT_DATE")
	private LocalDate registerDate = LocalDate.now();
	
	@Column(name= "activation_code")
	private String activationCode;
	
	@Column(name= "is_active", columnDefinition = "boolean default false")
	private boolean active = false;
	
	@Column(name= "is_deleted", columnDefinition = "boolean default false")
	private boolean deleted = false;

	public User(String email, String password, String activationCode) {
		this.email = email;
		this.password = password;
		this.activationCode = activationCode;
	}


}
