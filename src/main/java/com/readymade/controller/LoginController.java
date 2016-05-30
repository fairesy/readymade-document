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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.readymade.dao.UserDao;
import com.readymade.model.User;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/users/login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("user") @Valid User user, BindingResult result, HttpSession session, RedirectAttributes redir){
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()){
			logger.debug("error : {}", result.getFieldError().getDefaultMessage());
			mav.setViewName("redirect:/#/users/login");
			redir.addFlashAttribute("errorMessage", result.getFieldError().getDefaultMessage());
		}
		User loginUser = userDao.findByEmail(user.getEmail());

		if(loginUser != null){
			if(BCrypt.checkpw(user.getPassword(), loginUser.getPassword())){
				logger.debug("로그인 성공");
				session.setAttribute("user", user);
				mav.setViewName("redirect:/documents"); 
			}else{
				logger.debug("패스워드 불일치");
				redir.addFlashAttribute("errorMessage", "패스워드가 일치하지 않습니다!");
				redir.addFlashAttribute("email", user.getEmail());
				mav.setViewName("redirect:/#/users/login");
			}
		}else{
			logger.debug("해당 이메일로 가입된 유저 없음");
			redir.addFlashAttribute("errorMessage", "해당 이메일로 가입된 유저가 없습니다");
			mav.setViewName("redirect:/#/users/login");
		}
		return mav;
	}
	
	@RequestMapping(value="/users/logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return "redirect:/";
	}
}
