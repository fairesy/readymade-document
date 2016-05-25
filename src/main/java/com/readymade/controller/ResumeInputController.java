package com.readymade.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
			@RequestParam String email, @RequestParam String phone, HttpSession session){
		System.out.println("[personal information] : " + name_ko);
		
//		JSONObject personal = new JSONObject();
		
		String moduleType = "resume_personal";
		String data = name_ko + name_en + email + phone;
		System.out.println("session : " + session.getAttribute("user"));
		
//		Integer document_id = documentDao.findByUserId(1).getId();//test data. documentDao에서 documentId 얻어오는 방식 고민 
		Integer document_id = 1;
		
		Module personalModule = new Module(moduleType, data, document_id);
		//TODO 처음엔 insert, 그 다음부턴 update........
//		try {
//			moduleDao.insert(personalModule);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		return "redirect:/";
		
	}
}
