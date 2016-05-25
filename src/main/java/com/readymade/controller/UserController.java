package com.readymade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.readymade.dao.DocumentDao;
import com.readymade.dao.UserDao;
import com.readymade.model.Document;
import com.readymade.model.User;

@Controller
public class UserController {
	@Autowired
	UserDao userDao;
	
	@Autowired
	DocumentDao documentDao;
	
	@RequestMapping(value = "/users/join", method = RequestMethod.POST)
	public String post(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		User user = new User(name, email, password);
		
		try {
			User joined = userDao.insert(user);
			//문서종류가 하나이기때문에, 일단은 가입 시점에 document를 생성해서 가지고 있는다. 이후에 document 생성을 분리 
			documentDao.insert(new Document("resume_default", joined.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/#/login";
	}
}
