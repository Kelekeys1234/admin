package com.admin.admin.admistration;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.admin.admin.vo.Residant;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	  Optional<User> findByEmail(String email);

	    @Transactional
	    @Modifying
	    @Query("UPDATE User a " +
	            "SET a.enabled = TRUE WHERE a.email = ?1")
	    int enableUser(String email);

	
}
