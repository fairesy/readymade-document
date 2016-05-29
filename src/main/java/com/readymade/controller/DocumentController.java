package com.readymade.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readymade.dao.DocumentDao;
import com.readymade.dao.ModuleDao;
import com.readymade.dao.UserDao;
import com.readymade.model.Document;
import com.readymade.model.Module;
import com.readymade.model.User;

@Controller
public class DocumentController {
	private Logger logger = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	DocumentDao documentDao;
	@Autowired
	ModuleDao moduleDao;
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value="/documents", method = RequestMethod.GET)
	public ModelAndView documents(){
		return new ModelAndView("documents");
	}
	
	/* 1 : document type 중 resume type에 해당하는 id */
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
			Document newResume = documentDao.insert(new Document("resume_default", loginedUser.getId()));
			Integer documentId = newResume.getId();
			logger.debug("새로운 이력서를 만들었습니다!");
			
			//personal : 처음 한 번만 insert. 이후 edit때마다 update.
			ObjectMapper mapper = new ObjectMapper();
			HashMap<String, String> personal = new HashMap<String, String>();
			personal.put("phone", "");
			personal.put("email", "");
			personal.put("name_en", "");
			personal.put("name_ko", "");

			String data = mapper.writeValueAsString(personal);
			Module personalModule = new Module("resume_personal", data, documentId);
			moduleDao.insert(personalModule);
			logger.debug("personal module을 추가하였습니다.");
			logger.debug("personal information : {}", data);
			
			//education : '추가'버튼 누를 시 새로운 모듈 추가로 insert. 
			HashMap<String, String>education = new HashMap<String, String>();
			education.put("name", "");
			education.put("major", "");
			education.put("state", "");
			education.put("start_year", "");
			education.put("end_year", "");
			data = mapper.writeValueAsString(education);
			Module educationModule = new Module("resume_education", data, documentId);
			moduleDao.insert(educationModule);
			logger.debug("education module을 추가하였습니다.");
			logger.debug("education information : {}", data);
			
			//experience : '추가'버튼 누를 시 새로운 모듈 추가로 insert. 
			HashMap<String, String>experience = new HashMap<String, String>();
			experience.put("name", "");
			experience.put("description", "");
			experience.put("start_year", "");
			experience.put("start_month", "");
			experience.put("end_year", "");
			experience.put("end_month", "");
			experience.put("link", "");
			data = mapper.writeValueAsString(experience);
			Module experienceModule = new Module("resume_experience", data, documentId);
			moduleDao.insert(experienceModule);
			logger.debug("experience module을 추가하였습니다.");
			logger.debug("experience information : {}", data);
			
			//skills : 처음 한 번만 insert. 이후 edit때마다 update.
			HashMap<String, String>skills = new HashMap<String, String>();
			skills.put("skills", "");
			data = mapper.writeValueAsString(skills);
			Module skillModule = new Module("resume_skills", data, documentId);
			moduleDao.insert(skillModule);
			logger.debug("skill module을 추가하였습니다.");
			logger.debug("skill information : {}", data);

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
