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
import org.springframework.web.bind.annotation.ResponseBody;

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
	public @ResponseBody void savePersonalData(@RequestParam String name_ko, @RequestParam String name_en, 
			@RequestParam String email, @RequestParam String phone, HttpSession session){
		logger.debug("personal 데이터 입력");

		Integer resumeId = getResumeId(session);
		Module personalModule = moduleDao.findByDocumentId(resumeId, "resume_personal"); //null
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> personal = new HashMap<String, String>();
		personal.put("phone", phone);
		personal.put("email", email);
		personal.put("name_en", name_en);
		personal.put("name_ko", name_ko);
		try {
			String data = mapper.writeValueAsString(personal);
			logger.debug("personal data : {}", data);
			logger.debug("personal module : {}", personalModule);
			personalModule.setData(data); //null pointer exception 
			moduleDao.update(personalModule);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}


	@RequestMapping(value = "/education", method = RequestMethod.POST)
	public @ResponseBody void saveEducationData(@RequestParam String name, 
			@RequestParam String major, @RequestParam String state,
			@RequestParam String start_year, @RequestParam String end_year, HttpSession session){
		logger.debug("education 데이터 입력");
		Integer resumeId = getResumeId(session);
		Module educationModule = moduleDao.findByDocumentId(resumeId, "resume_education");
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> education = new HashMap<String, String>();
		education.put("name", name);
		education.put("major", major);
		education.put("state", state);
		education.put("start_year", start_year);
		education.put("end_year", end_year);
		try {
			String data = mapper.writeValueAsString(education);
			educationModule.setData(data);
			logger.debug("education data : {}", data);
			moduleDao.update(educationModule);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/experience", method = RequestMethod.POST)
	public @ResponseBody void saveExperiences(@RequestParam String name, @RequestParam String description,
			@RequestParam String start_year, @RequestParam String start_month,
			@RequestParam String end_year, @RequestParam String end_month,
			@RequestParam String link, HttpSession session){
		logger.debug("experience 데이터 입력");
		Integer resumeId = getResumeId(session);
		Module experienceModule = moduleDao.findByDocumentId(resumeId, "resume_experience");
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> experience = new HashMap<String, String>();
		experience.put("name", name);
		experience.put("description", description);
		experience.put("start", start_year + "." + start_month);
		experience.put("end", end_year + "." + end_month);
		experience.put("link", link);
		try {
			String data = mapper.writeValueAsString(experience);
			experienceModule.setData(data);
			logger.debug("education data : {}", data);
			moduleDao.update(experienceModule);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public @ResponseBody void saveSkillset(@RequestParam String skills, HttpSession session){
		logger.debug("skills 데이터 입력");
		Integer resumeId = getResumeId(session);
		Module skillModule = moduleDao.findByDocumentId(resumeId, "resume_skills");
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> skillset = new HashMap<String, String>();
		skillset.put("skills", skills);
		try {
			String data = mapper.writeValueAsString(skillset);
			skillModule.setData(data);
			logger.debug("skill data : {}", data);
			moduleDao.update(skillModule);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	private Integer getResumeId(HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		Document resume = documentDao.findResumeByUserId(loginedUser.getId());
		return resume.getId();
	}
}
