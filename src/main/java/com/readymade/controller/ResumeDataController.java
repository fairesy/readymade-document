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
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	UserDao userDao;
	@Autowired
	ModuleDao moduleDao;
	@Autowired
	DocumentDao documentDao;
	
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public @ResponseBody void savePersonalData(@RequestParam String name_ko, @RequestParam String name_en, 
			@RequestParam String email, @RequestParam String phone, HttpSession session) throws JsonProcessingException{
		logger.debug("personal 데이터 입력");
		Module personalModule = findModule(session, "resume_personal");
		String data = wrapPersonalData(name_ko, name_en, email, phone);
		updateModuleData(personalModule, data);
	}

	@RequestMapping(value = "/education", method = RequestMethod.POST)
	public @ResponseBody void saveEducationData(@RequestParam String name, 
			@RequestParam String major, @RequestParam String state,
			@RequestParam String start_year, @RequestParam String end_year, HttpSession session) throws JsonProcessingException{
		logger.debug("education 데이터 입력");
		Module educationModule = findModule(session, "resume_education");
		String data = wrapEducationData(name, major, state, start_year, end_year);
		updateModuleData(educationModule, data);
	}

	@RequestMapping(value = "/experience", method = RequestMethod.POST)
	public @ResponseBody void saveExperiences(@RequestParam String name, @RequestParam String description,
			@RequestParam String start_year, @RequestParam String start_month,
			@RequestParam String end_year, @RequestParam String end_month,
			@RequestParam String link, HttpSession session) throws JsonProcessingException{
		logger.debug("experience 데이터 입력");
		Module experienceModule = findModule(session, "resume_experience");
		String data = wrapExperienceData(name, description, start_year, start_month, end_year, end_month, link);
		updateModuleData(experienceModule, data);
	}

	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public @ResponseBody void saveSkillset(@RequestParam String skills, HttpSession session) throws JsonProcessingException{
		logger.debug("skills 데이터 입력");
		Module skillModule = findModule(session, "resume_skills");
		String data = wrapSkillsData(skills);
		updateModuleData(skillModule, data);
	}

	private void updateModuleData(Module module, String data) {
		module.setData(data);
		moduleDao.update(module);
	}

	private String wrapPersonalData(String name_ko, String name_en, String email, String phone) throws JsonProcessingException{
		HashMap<String, String> personal = new HashMap<String, String>();
		personal.put("phone", phone);
		personal.put("email", email);
		personal.put("name_en", name_en);
		personal.put("name_ko", name_ko);
		String data = mapper.writeValueAsString(personal);
		logger.debug("personal data : {}", data);
		return data;
	}

	private String wrapEducationData(String name, String major, String state, String start_year, String end_year)
			throws JsonProcessingException {
		HashMap<String, String> education = new HashMap<String, String>();
		education.put("name", name);
		education.put("major", major);
		education.put("state", state);
		education.put("start_year", start_year);
		education.put("end_year", end_year);
		
		String data = mapper.writeValueAsString(education);
		logger.debug("education data : {}", data);
		return data;
	}
	
	private String wrapExperienceData(String name, String description, String start_year, String start_month,
			String end_year, String end_month, String link) throws JsonProcessingException {
		HashMap<String, String> experience = new HashMap<String, String>();
		experience.put("name", name);
		experience.put("description", description);
		experience.put("start", start_year + "." + start_month);
		experience.put("end", end_year + "." + end_month);
		experience.put("link", link);
		
		String data = mapper.writeValueAsString(experience);
		logger.debug("experience data : {}", data);
		return data;
	}

	private String wrapSkillsData(String skills) throws JsonProcessingException {
		HashMap<String, String> skillset = new HashMap<String, String>();
		skillset.put("skills", skills);
		
		String data = mapper.writeValueAsString(skillset);
		logger.debug("skill data : {}", data);
		return data;
	}
	
	private Integer getResumeId(HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		Document resume = documentDao.findResumeByUserId(loginedUser.getId());
		return resume.getId();
	}
	
	private Module findModule(HttpSession session, String moduleType) {
		Integer resumeId = getResumeId(session);
		Module personalModule = moduleDao.findByDocumentId(resumeId, moduleType);
		return personalModule;
	}
}
