package com.greatlearning.studentmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleController {

	@RequestMapping("/")
	public String requestHandler() {
		// process the request , update the Model & return the view.

		return "simple";
	}

}
