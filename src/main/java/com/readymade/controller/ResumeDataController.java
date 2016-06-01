package com.readymade.controller;


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
import com.readymade.dao.DocumentDao;
import com.readymade.dao.ModuleDao;
import com.readymade.dao.UserDao;
import com.readymade.model.Document;
import com.readymade.model.Module;
import com.readymade.model.User;
import com.readymade.util.StaticUtil;

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
			@RequestParam String email, @RequestParam String phone, HttpSession session) throws JsonProcessingException{
		logger.debug("personal 데이터 입력");
		Module personalModule = findModule(session, "resume_personal");
		String data = StaticUtil.wrapPersonalData(name_ko, name_en, email, phone);
		updateModuleData(personalModule, data);
	}

	@RequestMapping(value = "/education", method = RequestMethod.POST)
	public @ResponseBody void saveEducationData(@RequestParam String name, 
			@RequestParam String major, @RequestParam String state,
			@RequestParam String start_year, @RequestParam String end_year, HttpSession session) throws JsonProcessingException{
		logger.debug("education 데이터 입력");
		Module educationModule = findModule(session, "resume_education");
		String data = StaticUtil.wrapEducationData(name, major, state, start_year, end_year);
		updateModuleData(educationModule, data);
	}
	
	@RequestMapping(value = "/education/create", method = RequestMethod.POST)
	public void createEducationModule(HttpSession session) throws Exception{
		String data = StaticUtil.wrapEducationData("", "", "", "", "");
		Module educationModule = new Module("resume_education", data, getResumeId(session));
		moduleDao.insert(educationModule);
		logger.debug("education module 추가 완료");
	}

	@RequestMapping(value = "/experience", method = RequestMethod.POST)
	public @ResponseBody void saveExperiences(@RequestParam String name, @RequestParam String description,
			@RequestParam String start_year, @RequestParam String start_month,
			@RequestParam String end_year, @RequestParam String end_month,
			@RequestParam String link, HttpSession session) throws JsonProcessingException{
		logger.debug("experience 데이터 입력");
		Module experienceModule = findModule(session, "resume_experience");
		String data = StaticUtil.wrapExperienceData(name, description, start_year, start_month, end_year, end_month, link);
		updateModuleData(experienceModule, data);
	}

	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public @ResponseBody void saveSkillset(@RequestParam String skills, HttpSession session) throws JsonProcessingException{
		logger.debug("skills 데이터 입력");
		Module skillModule = findModule(session, "resume_skills");
		String data = StaticUtil.wrapSkillsData(skills);
		updateModuleData(skillModule, data);
	}

	private void updateModuleData(Module module, String data) {
		module.setData(data);
		moduleDao.update(module);
	}
	
	public Integer getResumeId(HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		Document resume = documentDao.findResumeByUserId(loginedUser.getId());
		return resume.getId();
	}
	
	public Module findModule(HttpSession session, String moduleType) {
		Integer resumeId = getResumeId(session);
		Module personalModule = moduleDao.findByDocumentId(resumeId, moduleType);
		return personalModule;
	}

}
