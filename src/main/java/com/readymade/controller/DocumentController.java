package com.readymade.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.readymade.dao.DocumentDao;
import com.readymade.dao.ModuleDao;
import com.readymade.dao.UserDao;
import com.readymade.model.Document;
import com.readymade.model.Module;
import com.readymade.model.User;
import com.readymade.util.StaticUtil;

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
	public ModelAndView resumeManager(HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		logger.debug("로그인한 유저 : {}", loginedUser.getEmail());
		Document resume = documentDao.findByUserId(loginedUser.getId());
		if(resume != null){ 
			logger.debug("기존 이력서가 존재합니다. edit창으로 이동합니다.");
			mav.setViewName("redirect:/documents/1/edit");
			return mav;
		}else{
			Document newResume = documentDao.insert(new Document("resume_default", loginedUser.getId()));
			Integer documentId = newResume.getId();
			logger.debug("새로운 이력서를 만들었습니다!");

			String data = StaticUtil.wrapPersonalData("", "", "", "");
			Module personalModule = new Module("resume_personal", data, documentId);
			moduleDao.insert(personalModule);
			logger.debug("personal module을 추가하였습니다.");
			logger.debug("personal information : {}", data);
			
			data = StaticUtil.wrapEducationData("", "", "", "", "");
			Module educationModule = new Module("resume_education", data, documentId);
			moduleDao.insert(educationModule);
			logger.debug("education module을 추가하였습니다.");
			logger.debug("education information : {}", data);
			
			data = StaticUtil.wrapExperienceData("", "", "", "", "", "", "");
			Module experienceModule = new Module("resume_experience", data, documentId);
			moduleDao.insert(experienceModule);
			logger.debug("experience module을 추가하였습니다.");
			logger.debug("experience information : {}", data);

			data = StaticUtil.wrapSkillsData("");
			Module skillModule = new Module("resume_skills", data, documentId);
			moduleDao.insert(skillModule);
			logger.debug("skill module을 추가하였습니다.");
			logger.debug("skill information : {}", data);

			mav.setViewName("redirect:/documents/1/new");
			return mav;
		}
	}
	
	@RequestMapping(value="/documents/1/new", method = RequestMethod.GET)
	public ModelAndView createResume(){
		return new ModelAndView("resume");
	}
	@RequestMapping(value="/documents/1/edit", method = RequestMethod.GET)
	public ModelAndView editResume(HttpSession session) throws JsonProcessingException, IOException{
		ModelAndView mav = new ModelAndView();
		
		Integer resumeId = getResumeId(session);
		
		String personalData = moduleDao.findByDocumentId(resumeId, "resume_personal").getData();
		String educationData = moduleDao.findByDocumentId(resumeId, "resume_education").getData();
		String experienceData = moduleDao.findByDocumentId(resumeId, "resume_experience").getData();
		String skillsData = moduleDao.findByDocumentId(resumeId, "resume_skills").getData();
		logger.debug("personal Data 꺼내오기 : {}", personalData);

		mav.setViewName("resume");
		mav.addObject("editMode", true);
		mav.addObject("personal", personalData);
		mav.addObject("education", educationData);
		mav.addObject("experience", experienceData);
		mav.addObject("skills", skillsData);
		return mav;
	}
	
	public Integer getResumeId(HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		Document resume = documentDao.findResumeByUserId(loginedUser.getId());
		return resume.getId();
	}
	
}
