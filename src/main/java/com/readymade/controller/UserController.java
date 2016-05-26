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
import com.readymade.model.Document;
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
		User newUser = new User(user.getName(), user.getEmail(), hashedPassword);

		try {
			User joined = userDao.insert(newUser);
			//문서종류가 하나이기때문에, 일단은 가입 시점에 document를 생성해서 가지고 있는다. 이후에 document 생성을 분리 
			documentDao.insert(new Document("resume_default", joined.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/#/login";
	}
}
