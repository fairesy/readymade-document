package com.readymade.controller;

import javax.servlet.http.HttpSession;
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

import com.readymade.dao.UserDao;
import com.readymade.model.User;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/users/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") @Valid User user, BindingResult result, HttpSession session){
		if(result.hasErrors()){
			logger.debug("error : {}", result);
			return "redirect:/#/login";
		}
		User loginUser = userDao.findByEmail(user.getEmail());
		
		if(loginUser != null){
			if(BCrypt.checkpw(user.getPassword(), loginUser.getPassword())){
				logger.debug("로그인 성공");
				session.setAttribute("user", user);
				return "redirect:/";
			}else{
				logger.debug("패스워드 불일치");
				return "redirect:/#/login";
			}
		}else{
			logger.debug("해당 이메일로 가입된 유저 없음");
			return "redirect:/#/login";
		}
		//TODO "패스워드가 일치하지 않습니다", "가입된 유저가 아닙니다" 등 메시지 출력
	}
	
	@RequestMapping(value="/users/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/";
	}
}
