package com.njifanda.auth.Web.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.njifanda.auth.Dtos.LoginDto;
import com.njifanda.auth.Models.User;
import com.njifanda.auth.Services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	
	// Name of key content user authentification
	private String authSession = "auth";
	
    // Add once service is implemented:
    @Autowired
    private UserService userService;
	
	public HomeController() {}
	
    @GetMapping("/home")
    public String home(
    		Model model,
    		HttpSession session
    ) {
    
        User user = (User) session.getAttribute(this.authSession);
        if (user == null) {

        	return "redirect:/";
        }

        model.addAttribute("auth", user);
        return "home.jsp";
    }
    
    @GetMapping("/")
    public String index(
    		Model model,
    		HttpSession session
    ) {
    	
    	// Check if user already login, if session have user, redirect to home page
        User user = (User) session.getAttribute(this.authSession);
        if (user != null) {

        	return "redirect:/home";
        }
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("user", new User());
        model.addAttribute("login", new LoginDto());
        return "index.jsp";
    }
    
    @PostMapping("/register")
    public String register(
    		@Valid @ModelAttribute("user") User user, 
            BindingResult result, Model model, HttpSession session
    ) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("login", new LoginDto());
            return "index.jsp";
        }

        User register = this.userService.register(user, result);
        session.setAttribute(this.authSession, register);
    
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(
    		@Valid @ModelAttribute("login") LoginDto login, 
            BindingResult result,
            Model model,
            HttpSession session
    ) {
        
        // Add once service is implemented:
         User user = this.userService.login(login, result);
    
        if(result.hasErrors() || user == null) {

            model.addAttribute("user", new User());
            return "index.jsp";
        }

        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.    
        session.setAttribute(this.authSession, user);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {

    	Object user = session.getAttribute(this.authSession);
    	if (user != null) {
    		session.setAttribute(authSession, null); // Delete user in session
    		// session.removeAttribute(authSession); // Another method
    	}
    	return "redirect:/";
    }

}
