package com.admin.admin.registration;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.admin.admin.admistration.AuthenticationRequest;
import com.admin.admin.admistration.UserService;
import com.admin.admin.util.JwtUtil;
import com.admin.admin.vo.Residant;
import lombok.AllArgsConstructor;




@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegisterController {
   @Autowired
	RegistrationService registrationService;
  @Autowired
   AuthenticationManager authenticationManager;
   @Autowired
  UserService appuserService;
   @Autowired 
   JwtUtil jwtUtil;
   @Autowired
   RestTemplate restTemplate;

  private String url ="http://localhost:8082/resident/getAllResident";
  
  private String creates ="http://localhost:8082/resident/SaveResident";

// register new users
	@PostMapping()
    public String register(@RequestBody Registration request) {
        return registrationService.register(request);
    }
    //token confirmation for new Users
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
    
    //Jwt authentication
    @PostMapping("/authentication")
	 public String generateToken(@RequestBody AuthenticationRequest auth) throws Exception {
		 try {
		 authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(auth.getEmail(),auth.getPassword())
				);
		 }catch(Exception e) {
			 throw new Exception("user not found");
			 
		 }
		return jwtUtil.generateToken(auth.getEmail());
}
   
    
    
    
    
    
    
    
    
    
}
