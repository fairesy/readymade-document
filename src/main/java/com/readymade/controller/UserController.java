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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView post(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redir) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()){
			result.getFieldError().getDefaultMessage();
			logger.debug("error : {}", result.getFieldError().getDefaultMessage());
			mav.setViewName("redirect:/#/users/form");
			redir.addFlashAttribute("errorMessage", result.getFieldError().getDefaultMessage());
			return mav;
		}
		
		if(userDao.findByEmail(user.getEmail()) != null){
			logger.debug("이미 가입된 이메일입니다.");
			mav.setViewName("redirect:/#/users/form");
			redir.addFlashAttribute("errorMessage", "이미 가입된 이메일입니다.");
			return mav;
		}
		
		logger.debug("user : {}", user);
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		User newUser = new User(user.getEmail(), hashedPassword);

		userDao.insert(newUser);
		logger.debug("새로운 유저 가입이 완료되었습니다.");

		mav.setViewName("redirect:/#/users/login");
		
		return mav;
	}
}
