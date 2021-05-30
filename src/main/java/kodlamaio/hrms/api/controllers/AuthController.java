package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.AuthService;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.dtos.EmployerForRegisterDto;
import kodlamaio.hrms.entities.dtos.CandidateForRegisterDto;
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
    
    @PostMapping("/loginCandidate")
    public Result loginCandidate(@RequestBody UserForLoginDto userForLoginDto) {
        return authService.loginCandidate(userForLoginDto);
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

    @PostMapping("/candidateRegister")
    public Result registerForCandidate(@RequestBody CandidateForRegisterDto candidateForRegisterDto) {
        return authService.candidateRegister(candidateForRegisterDto);
    }

    @PostMapping("/confirmActivation")
    public Result confirmActivation(@RequestBody String email, String activationCode) {
        return authService.confirmActivation(email, activationCode);
    }

}
