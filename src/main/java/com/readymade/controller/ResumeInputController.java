package com.readymade.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readymade.dao.DocumentDao;
import com.readymade.dao.ModuleDao;
import com.readymade.model.Module;
import com.readymade.model.User;

@Controller
@RequestMapping("/resume/data")
public class ResumeInputController {
	
	@Autowired
	ModuleDao moduleDao;
	@Autowired
	DocumentDao documentDao;
	
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public String savePersonalInfo(@RequestParam String name_ko, @RequestParam String name_en, 
			@RequestParam String email, @RequestParam String phone, HttpSession session) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> personal = new HashMap<String, String>();
		personal.put("phone", phone);
		personal.put("email", email);
		personal.put("name_en", name_en);
		personal.put("name_ko", name_ko);
//		JSONObject personal = new JSONObject();

		String moduleType = "resume_personal";
		String data;
//		Integer document_id = documentDao.findByUserId(1).getId();//documentDao에서 documentId 얻어오는 방식(시점?) 고민 
		Integer document_id = 1;//test data
		try {
			data = mapper.writeValueAsString(personal);
			System.out.println("[personal information] : " + data);
			Module personalModule = new Module(moduleType, data, document_id);
//			moduleDao.insert(personalModule);//TODO 처음엔 insert, 그 다음부턴 update
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return "redirect:/";
		
	}
}
