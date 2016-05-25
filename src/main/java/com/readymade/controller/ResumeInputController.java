package com.readymade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.readymade.dao.ModuleDao;
import com.readymade.model.Module;

@Controller
@RequestMapping("/resume/data")
public class ResumeInputController {
	
	@Autowired
	ModuleDao moduleDao;
	//DocumentDao 필요 
	
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public String savePersonalInfo(@RequestParam String name_ko, @RequestParam String name_en, 
			@RequestParam String email, @RequestParam String phone){
		System.out.println("[personal information] : " + name_ko);
		
//		JSONObject personal = new JSONObject();
		
		String moduleType = "resume_personal";
		String data = name_ko + name_en + email + phone;
		Integer document_id = 1;//test data 
		
		Module personalModule = new Module(moduleType, data, document_id);
		try {
			moduleDao.insert(personalModule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
		
	}
}
