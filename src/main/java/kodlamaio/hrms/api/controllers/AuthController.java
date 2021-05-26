package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.JobSeekerForRegisterDto;
import kodlamaio.hrms.entities.dtos.UserForLoginDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public Result login(@RequestBody UserForLoginDto userForLoginDto) {
        return authService.login(userForLoginDto);
    }
    
    @PostMapping("/loginjobseeker")
    public Result loginJobSeeker(@RequestBody UserForLoginDto userForLoginDto) {
        return authService.loginJobSeeker(userForLoginDto);
    }
    
    @PostMapping("/loginemployer")
    public Result loginEmployer(@RequestBody UserForLoginDto userForLoginDto) {
        return authService.loginEmployer(userForLoginDto);
    }
    
    @PostMapping("/loginemployee")
    public Result loginEmployee(@RequestBody UserForLoginDto userForLoginDto) {
        return authService.loginEmployee(userForLoginDto);
    }

    @PostMapping("/employerregister")
    public Result registerForEmployer(@RequestBody EmployerForRegisterDto employerForRegisterDto) {
        return authService.employerRegister(employerForRegisterDto);
    }

    @PostMapping("/jobSeekerregister")
    public Result registerForJobSeeker(@RequestBody JobSeekerForRegisterDto jobSeekerForRegisterDto) {
        return authService.jobSeekerRegister(jobSeekerForRegisterDto);
    }

    @PostMapping("/comfirmactivation")
    public Result comfirmActivation(@RequestBody String email, String activationCode) {
        return authService.comfirmActivation(email, activationCode);
    }

}
