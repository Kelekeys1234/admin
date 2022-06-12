package com.admin.admin.admistration;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.admin.admin.registration.token.ConfirmationToken;
import com.admin.admin.registration.token.ConfirmationTokenService;
import com.admin.admin.vo.Residant;
import com.admin.admin.vo.ResponseTemplateVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService  {
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

     @Autowired
    private UserRepository appUserRepository;
     @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
     @Autowired
    private  ConfirmationTokenService confirmationTokenService;
     //instance
    private ConfirmationToken confirmationToken = new  ConfirmationToken();
     @Autowired
     UserRepository userRepository;
   
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
        	
        	 if (confirmationToken.getConfirmedAt() != null) {
        		 throw new IllegalStateException("email already taken");
        		 
             }
        	 String token = UUID.randomUUID().toString();
    		 ConfirmationToken confirmationToken = new ConfirmationToken(
    	                token,
    	                LocalDateTime.now(),
    	                LocalDateTime.now().plusMinutes(15),
    	                userRepository.findByEmail(appUser.getEmail()).get()
    	        );

    	        confirmationTokenService.saveConfirmationToken(
    	                confirmationToken);
            
    	        return token;
           
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        

//        TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableUser(email);
    }
   
	
}
