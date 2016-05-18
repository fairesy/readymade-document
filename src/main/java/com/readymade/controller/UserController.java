package com.readymade.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.readymade.dao.UserDao;
import com.readymade.model.User;

@Controller
public class UserController {
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = "/users/join", method = RequestMethod.POST)
	public String post(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		//브라우저에서 작성한 회원가입 폼의 내용을 받아서,
		//Model에 담고,
		User user = new User(name, email, password);
		
		//Model에 담은 데이터를 데이터베이스에 넘겨준다.
		try {
			User joinedUser = userDao.insert(user);
			System.out.println(joinedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//다 하고 나면 로그인 화면으로 가기! 
		return "redirect:/#/login";
	}
}
