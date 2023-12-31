package com.njifanda.auth.Services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.njifanda.auth.Dtos.LoginDto;
import com.njifanda.auth.Models.User;
import com.njifanda.auth.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    
    // TO-DO: Write register and login methods!
    public User register(User user, BindingResult result) {

    	if(!user.validePassword()) {

    	    result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
    	}
    	
    	Optional<User> verifyEmail = this.userRepository.findByEmail(user.getEmail());
    	if (verifyEmail.isPresent()) {

    		result.rejectValue("email", "Exist", "The Email already exist in the system!");
    	}
    	
    	if(result.hasErrors()) {

    	    return (User) result;
    	}
    	
    	String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    	user.setPassword(hashed);
    	return this.userRepository.save(user);
    }

    public User login(LoginDto login, BindingResult result) {

    	Optional<User> verifyEmail = this.userRepository.findByEmail(login.getEmail());
    	if (!verifyEmail.isPresent()) {

    		result.rejectValue("email", "Not found", "The Email not exist in the system!");
    	} else {

    		User user = verifyEmail.get(); // Convert Optional<User> to User
    		String password = login.getPassword(); // Password send by user in the form
    		String userHashedPassword = user.getPassword(); // Password content in the database
    		if (BCrypt.checkpw(password, userHashedPassword)) { // Verify if password equal to hashed password

    			return user;
    		} else {
    			result.rejectValue("password", "Matches", "The Password is not match!");
    		}
    	}
    	
    	return null;
    }
}
