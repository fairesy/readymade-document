package com.readymade.util;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readymade.controller.ResumeDataController;
import com.readymade.dao.DocumentDao;
import com.readymade.dao.ModuleDao;
import com.readymade.dao.UserDao;

public class StaticUtil {
	private static Logger logger = LoggerFactory.getLogger(ResumeDataController.class);
	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	static UserDao userDao;
	@Autowired
	static ModuleDao moduleDao;
	@Autowired
	static DocumentDao documentDao;
	
	public static String wrapPersonalData(String name_ko, String name_en, String email, String phone) throws JsonProcessingException{
		HashMap<String, String> personal = new HashMap<String, String>();
		personal.put("phone", phone);
		personal.put("email", email);
		personal.put("name_en", name_en);
		personal.put("name_ko", name_ko);
		String data = mapper.writeValueAsString(personal);
		logger.debug("personal data : {}", data);
		return data;
	}

	public static String wrapEducationData(String name, String major, String state, String start_year, String end_year)
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
	
	public static String wrapExperienceData(String name, String description, String start_year, String start_month,
			String end_year, String end_month, String link) throws JsonProcessingException {
		HashMap<String, String> experience = new HashMap<String, String>();
		experience.put("name", name);
		experience.put("description", description);
		experience.put("start_year", start_year);
		experience.put("start_month", start_month);
		experience.put("end_year", end_year);
		experience.put("end_month", end_month);
		experience.put("link", link);
		
		String data = mapper.writeValueAsString(experience);
		logger.debug("experience data : {}", data);
		return data;
	}

	public static String wrapSkillsData(String skills) throws JsonProcessingException {
		HashMap<String, String> skillset = new HashMap<String, String>();
		skillset.put("skills", skills);
		
		String data = mapper.writeValueAsString(skillset);
		logger.debug("skill data : {}", data);
		return data;
	}

}
