package com.admin.admin.vo;

import com.admin.admin.admistration.User;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ResponseTemplateVo {
  private User user;
  private Residant residant;
  
  
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Residant getResidant() {
	return residant;
}
public void setResidant(Residant residant) {
	this.residant = residant;
}
public ResponseTemplateVo() {
	super();
}
public ResponseTemplateVo(Residant residant) {
	super();
	this.residant = residant;
}
  

  
}
