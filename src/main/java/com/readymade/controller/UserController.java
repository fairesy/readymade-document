package com.readymade.controller;


import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.readymade.dao.DocumentDao;
import com.readymade.dao.UserDao;
import com.readymade.model.User;


@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DocumentDao documentDao;
	
	@RequestMapping(value = "/users/join", method = RequestMethod.POST)
	public String post(@ModelAttribute("user") @Valid User user, BindingResult result) {
		
		if(result.hasErrors()){
			logger.debug("error : {}", result);
			return "redirect:/#/users/form";
		}
		
		logger.debug("user : {}", user);
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		logger.debug("hashed : {}", hashedPassword);
		User newUser = new User(user.getEmail(), hashedPassword);

		try {
			userDao.insert(newUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/#/users/login";
	}
}
