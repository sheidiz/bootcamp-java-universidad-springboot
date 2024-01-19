package ar.com.educacionit.movie.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

	// http://localhost:8080 > viene aca!

	@RequestMapping(value= {"/","/pepe"}, method = RequestMethod.GET)
	public String home() {
		//redirecciona al src/main/resources/static/index.html
		return "index";//ext .html
	}
}