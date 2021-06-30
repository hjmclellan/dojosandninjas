package com.hmclellan.dojosandninjas.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hmclellan.dojosandninjas.models.Dojo;
import com.hmclellan.dojosandninjas.models.Ninja;
import com.hmclellan.dojosandninjas.services.DojoService;
import com.hmclellan.dojosandninjas.services.NinjaService;

@Controller
public class IndexController {
	private final NinjaService ninjaService;
	private final DojoService dojoService;
	
	public IndexController(NinjaService ninjaService, DojoService dojoService) {
		this.ninjaService = ninjaService;
		this.dojoService = dojoService;
	}
	
	@RequestMapping("/dojos/new")
	public String newDojo(Model model) {
		Dojo dojo = new Dojo();
		model.addAttribute("dojo", dojo);
		return "/pages/dojo.jsp";
	}
	
	@RequestMapping(value="/dojos/new", method=RequestMethod.POST)
	public String createDojo(@RequestParam(value="name") String name) {
		Dojo dojo = new Dojo(name);
		dojoService.createDojo(dojo);
		return "redirect:/dojos/new";
	}
	
	@RequestMapping("/ninjas/new")
	public String newNinja(Model model) {
		Ninja ninja = new Ninja();
		List<Dojo> dojos = dojoService.findAll();
		model.addAttribute("ninja", ninja);
		model.addAttribute("dojos", dojos);
		return "/pages/ninja.jsp";
	}
	
	@RequestMapping(value="/ninjas/new", method=RequestMethod.POST)
	public String createNinja(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="age") Integer age, @RequestParam(value="dojo") Long id) {
		Dojo dojo = dojoService.findDojo(id);
		Ninja ninja = new Ninja(firstName,lastName,age,dojo);
		ninjaService.createNinja(ninja);
		return "redirect:/ninjas/new";
	}
	
	@RequestMapping("/dojos/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Dojo dojo = dojoService.findDojo(id);
		List<Ninja> ninjas = ninjaService.findAllByDojo(dojo);
		model.addAttribute("dojo", dojo);
		model.addAttribute("ninjas", ninjas);
		return "/pages/index.jsp";
	}
	
}