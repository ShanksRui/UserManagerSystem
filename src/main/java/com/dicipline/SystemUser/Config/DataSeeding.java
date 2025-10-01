package com.dicipline.SystemUser.Config;
import java.time.Instant;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dicipline.SystemUser.Entities.DataUser;
import com.dicipline.SystemUser.Entities.User;
import com.dicipline.SystemUser.Repositories.DataUserRepository;
import com.dicipline.SystemUser.Repositories.UserRepository;
@Configuration
@Profile({"prod"})
public class DataSeeding implements CommandLineRunner {
	     
	    @Autowired
	    private UserRepository userRepository;
	    @Autowired
	    private DataUserRepository dataUserRepository;
	
	     public void run(String... args) throws Exception {
	     User u1 = new User(null, "Alice Silva", "12345", LocalDate.of(2000, 5, 14));
	     DataUser du = new DataUser(null, "Windows", "01001000", Instant.parse("2025-09-07T15:30:00Z"), Instant.parse("2025-09-15T23:30:00Z"), u1);
        
	      userRepository.save(u1);
	      dataUserRepository.save(du);
	  }
	
}
