package com.admin.admin.registration.token;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.admin.admin.admistration.User;

@Entity
@Table(name="confirmationTable")

public class ConfirmationToken {
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    
    private String token;

   
  
   

    private LocalDateTime confirmedAt;
    
    private LocalDateTime ExpiresAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User appUser;

    

	

	

	

	public ConfirmationToken() {
		super();
	}

	

	public ConfirmationToken(String token, LocalDateTime confirmedAt, LocalDateTime expiresAt, User appUser) {
		super();
		this.token = token;
		this.confirmedAt = confirmedAt;
		ExpiresAt = expiresAt;
		this.appUser = appUser;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	

	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(LocalDateTime confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public User getAppUser() {
		return appUser;
	}

	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}

	public LocalDateTime getExpiresAt() {
		return ExpiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		ExpiresAt = expiresAt;
	}

	
	}
	
    

