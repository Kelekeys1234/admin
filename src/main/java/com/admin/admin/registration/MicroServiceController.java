package com.admin.admin.registration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.admin.admin.admistration.User;
import com.admin.admin.admistration.UserRepository;
import com.admin.admin.vo.Email;
import com.admin.admin.vo.Facility;
import com.admin.admin.vo.Residant;
import com.admin.admin.vo.Rooms;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "microservices")
@AllArgsConstructor
public class MicroServiceController {
	 @Autowired
	 private  RestTemplate restTemplate;
	 @Autowired
	private UserRepository userRepository;

	 
	
	  private String url ="http://localhost:8083/resident/getAllResident";
	  
	  private String facility ="http://localhost:8085/facility/addFacility";
	  // get all residantMicroServices
    @GetMapping("residan")
    public List<Object> saveNewResident() throws Exception {
		Object[] getResident = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(getResident);
    	
    }
    // Create residantMicroServices
   
    // delete residant
    @DeleteMapping("delete/{id}")
    public void deleteResidant(@PathVariable("id") Long id) throws Exception {
     String del ="http://localhost:8083/resident/delete/" + id; 
     if(id ==null) {
    	 new Exception ("Empty in The DataBase");
     }
    	Map<String , Long> param = new HashMap<>();
    	param.put("id", id);
    	  restTemplate.delete(del, param);
    	
    	Email emails = new Email("fkelenna@gmail.com","You just Remove an resident","Residant Notification");
    	restTemplate.postForEntity("http://localhost:8081/sendEmail", emails, String.class);
    }
    
    //add facility
    @PostMapping("facility")
    public ResponseEntity<Facility>  createResidant(@RequestBody Facility obj) {
    Facility res = new Facility(obj.getFacilityName(),obj.Description,obj.CreatedOn,obj.CreatedBy) ;   	
    	
    	ResponseEntity<Facility> ob =restTemplate.postForEntity(facility,res, Facility.class);

    	return ob;
    	
    }
    //getFAcility
    @GetMapping("getFacilty")
    public List<Object> listFacility(){
    	Object[] obj = restTemplate.getForObject("http://localhost:8085/facility/getAllfacility", Object[].class);
    	return Arrays.asList(obj);
    }
    
    //get allRooms
    @GetMapping("/getAllRooms")
    public List<Object> listOfRooms(){
    	Object[] obj = restTemplate.getForObject("http://localhost:8085/rooms/getAllRoom", Object[].class);
    	return Arrays.asList(obj);
    }
    
    
    
    
    @PostMapping("saveRoom/{id}")
    public ResponseEntity<Rooms>  createRoom(@RequestBody Rooms obj ,@PathVariable("id") long id) {
    	
  Rooms res = new Rooms(obj.getRoomNumber()) ;   	
    	ResponseEntity<Rooms> ob =restTemplate.postForEntity("http://localhost:8085/rooms/save/" + id,res, Rooms.class);

    	return ob;
    	
    }
    @PostMapping("sendMail")
    public ResponseEntity<String> saveEmail(@RequestBody Email email) throws RestClientException{
    	Email emails = new Email(email.getEmail(),email.getText(),email.getSubject());
    
    	ResponseEntity<String> ob = restTemplate.postForEntity("http://localhost:8081/sendEmail", emails, String.class);
    	return ob;
    	
    	
    }
    @PostMapping("addResidant/{id}")
    public ResponseEntity<Residant> saveResidant(@RequestBody Residant residant ,@PathVariable("id") Long id) throws Exception{
    	Residant residants = new Residant(residant.getResidantName(),residant.getDiscription());
    	ResponseEntity<Residant> res = restTemplate.postForEntity("http://localhost:8083/resident/SaveResident", residants, Residant.class);
    	User user = userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
    	Email emails = new Email(user.getEmail(),residant.getResidantName() + " was added to our database","Residant Notification");
    	restTemplate.postForEntity("http://localhost:8081/sendEmail", emails, String.class);
    	
		return res;
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
