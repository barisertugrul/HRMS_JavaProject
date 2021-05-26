package kodlamaio.hrms.entities.dtos;

import kodlamaio.hrms.entities.abstracts.ValidatableUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerForRegisterDto implements ValidatableUser {
    private String firstName;
    private String lastName;
    private String nationalityId;
    private Integer yearOfBirth;
    private String email;
    private String password;
    private String repassword;
}
