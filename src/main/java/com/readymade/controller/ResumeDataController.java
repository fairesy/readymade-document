package com.readymade.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readymade.dao.DocumentDao;
import com.readymade.dao.ModuleDao;
import com.readymade.dao.UserDao;
import com.readymade.model.Document;
import com.readymade.model.Module;
import com.readymade.model.User;

@Controller
@RequestMapping("/resume/data")
public class ResumeDataController {
	
	private Logger logger = LoggerFactory.getLogger(ResumeDataController.class);
	
	@Autowired
	UserDao userDao;
	@Autowired
	ModuleDao moduleDao;
	@Autowired
	DocumentDao documentDao;
	
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public void savePersonalInfo(@RequestParam String name_ko, @RequestParam String name_en, 
			@RequestParam String email, @RequestParam String phone, HttpSession session) throws JsonProcessingException{
		int resumeId = getResumeId((User) session.getAttribute("user"));
		
	}

	private Integer getResumeId(User sessionUser) {
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		Document resume = documentDao.findResumeByUserId(loginedUser.getId());
		return resume.getId();
	}
	
	@RequestMapping(value = "/education", method = RequestMethod.POST)
	public void saveEducationInfo(){
	}
	
	@RequestMapping(value = "/experience", method = RequestMethod.POST)
	public void saveExperiences(){
	}
	
	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public void saveSkillset(){
	}
}
