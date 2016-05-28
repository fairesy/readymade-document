package com.readymade.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentController {
	@RequestMapping(value="/documents", method = RequestMethod.GET)
	public ModelAndView documents(){
		return new ModelAndView("documents");
	}
	
	@RequestMapping(value="/documents/1/new", method = RequestMethod.GET)
	public ModelAndView resume(){
		return new ModelAndView("resume");
	}
}
