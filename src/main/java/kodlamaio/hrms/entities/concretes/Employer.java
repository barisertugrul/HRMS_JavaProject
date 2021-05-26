package kodlamaio.hrms.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
@Table(name="employers")
public class Employer extends User{
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="website")
	private String website;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="admin_comfirm", columnDefinition = "boolean default false")
	private boolean adminComfirm = false;


    public Employer(
    		String email, 
    		String password, 
    		String companyName,
    		String website,
    		String phoneNumber,
    		String activationCode) {
        super(email, password, activationCode);
        this.companyName = companyName;
        this.website = website;
        this.phoneNumber = phoneNumber;
    }

}
