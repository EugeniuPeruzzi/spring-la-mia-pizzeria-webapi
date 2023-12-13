package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/pizza")
public class PizzaRestcontroller {
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex(Model model,
			@RequestParam(required = false) String p){
		List<Pizza> pizzas = p == null  
				? pizzaService.findAll()
				: pizzaService.findByName(p);
	
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	
}
