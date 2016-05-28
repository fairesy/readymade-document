package com.readymade.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.readymade.dao.DocumentDao;
import com.readymade.dao.UserDao;
import com.readymade.model.Document;
import com.readymade.model.User;

@Controller
public class DocumentController {
	private Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	DocumentDao documentDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/documents", method = RequestMethod.GET)
	public ModelAndView documents(){
		return new ModelAndView("documents");
	}
	
	/* 1 : document타입들 중 resume에 부여한 아이디*/
	@RequestMapping(value="/documents/1", method = RequestMethod.GET)
	public String resumeManager(HttpSession session) throws Exception{
		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		logger.debug("로그인한 유저 : {}", loginedUser.getEmail());
		Document resume = documentDao.findByUserId(loginedUser.getId());
		if(resume != null){ 
			logger.debug("기존 이력서가 존재합니다. edit창으로 이동합니다.");
			return "redirect:/documents/1/edit";
		}else{
			logger.debug("새로운 이력서를 만들기 시작합니다!");
			documentDao.insert(new Document("resume_default", loginedUser.getId()));
			return "redirect:/documents/1/new";
		}
	}
	
	@RequestMapping(value="/documents/1/new", method = RequestMethod.GET)
	public ModelAndView createResume(){
		return new ModelAndView("resume");
	}
	@RequestMapping(value="/documents/1/edit", method = RequestMethod.GET)
	public ModelAndView editResume(){
		return new ModelAndView("resume");
	}
}
