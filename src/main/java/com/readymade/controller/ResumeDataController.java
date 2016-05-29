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
	public @ResponseBody void savePersonalInfo(@RequestParam String name_ko, @RequestParam String name_en, 
			@RequestParam String email, @RequestParam String phone, HttpSession session){
		logger.debug("퍼스널 데이터 인풋 받아오기!");

		User sessionUser = (User) session.getAttribute("user");
		User loginedUser = userDao.findByEmail(sessionUser.getEmail());
		logger.debug("session user : {}", sessionUser);
		logger.debug("session user mail : {}", sessionUser.getEmail());
		logger.debug("logined user : {}", loginedUser);
		logger.debug("logined user id : {}", loginedUser.getId());
		Document resume = documentDao.findResumeByUserId(loginedUser.getId());
		/*resume.getId는 왜 null이죠.....?!?!?!?!?!?!?!?!?!?!?!?!?*/
		Module personalModule = moduleDao.findResumePersonalByDocumentId(2);//2라니......resume id를 가지ㅗㄱ 오라고...
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> personal = new HashMap<String, String>();
		personal.put("phone", phone);
		personal.put("email", email);
		personal.put("name_en", name_en);
		personal.put("name_ko", name_ko);
		try {
			String data = mapper.writeValueAsString(personal);
			personalModule.setData(data);
			logger.debug("personal data : {}", data);
			moduleDao.updatePersonal(personalModule);//document_id
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/education", method = RequestMethod.POST)
	public @ResponseBody void saveEducationInfo(){
		logger.debug("에쥬케이션 데이터 인풋 받아오기!");
	}
	
	@RequestMapping(value = "/experience", method = RequestMethod.POST)
	public void saveExperiences(){
	}
	
	@RequestMapping(value = "/skills", method = RequestMethod.POST)
	public void saveSkillset(){
	}
}
