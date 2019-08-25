package com.studentrecords;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class StudentController {
	ApplicationContext context;
	StudentTemplate studentJDBCTemplate;

	@RequestMapping(value = "/Student", method = RequestMethod.GET)
	public ModelAndView student() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		studentJDBCTemplate = (StudentTemplate) context.getBean("studentJDBCTemplate");
		return new ModelAndView("Student", "command", new Student());
	}

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("SpringWeb") Student student, ModelMap model, HttpServletRequest request) {
		model.addAttribute("name", student.getName());
		model.addAttribute("age", student.getAge());
		model.addAttribute("id", student.getId());

		System.out.println("creating record for " + student.getName());
		studentJDBCTemplate.create(student);
		return "Result";
	}

	/*
	 * @RequestMapping(value = "/loadJson", method = RequestMethod.POST)
	 * public @ResponseBody String loadJson() { // load data using
	 * List<FoodDataDao> foodDatalist = new ArrayList<>();
	 * FoodTruckDataProcessor processor = new FoodTruckDataProcessor();
	 * 
	 * foodDatalist = processor.loadJson();
	 * 
	 * return foodDatalist.toString(); }
	 */

	@RequestMapping(value = "/loadJson", method = RequestMethod.POST)
	public ModelAndView loadJson(@ModelAttribute("SpringWeb") ModelMap model, HttpServletResponse response) {
		// load data using
		List<FoodDataDao> foodDatalist = new ArrayList<>();
		FoodTruckDataProcessor processor = new FoodTruckDataProcessor();
		foodDatalist = processor.loadJson();
		Gson gson = new Gson();
		String jsonList = gson.toJson(foodDatalist);
		// model.addAttribute("foodDataList", jsonList.toString());
		response.setContentType("application/json");
		return new ModelAndView("foodData", "jsonList", jsonList);
	}
}