package com.readymade.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.readymade.dao.UserDao;
import com.readymade.model.User;

@Controller
public class LoginController {
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/users/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session){
		
		//id(email),pw 확인하기
		User user = userDao.findByEmail(email);
		if(user != null){
			if(password.equals(user.getPassword())){
				//로그인 성공 : session에 로그인한 유저 정보 담아서 보내기 
				session.setAttribute("user", user);
				return "redirect:/";
			}else{
				//password 불일치 
				return "redirect:/#/login";
			}
		}else{
			//해당 이메일로 가입된 유저 없음 
			return "redirect:/#/login";
		}
		//TODO "패스워드가 일치하지 않습니다", "가입된 유저가 아닙니다" 등 메시지 출력
	}
}
