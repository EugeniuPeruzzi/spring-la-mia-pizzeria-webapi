package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
// Dichiaro la rotta generale della API
@RequestMapping("/api/pizza")
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    // Utilizzo la rotta /api/pizza per prendere tutte le pizze e fare la chiamata api.
    // Se nella chiamata api utilizzo : ?p={pizza.nome} mi restituisce la pizza specifica cercata.
    @GetMapping
    public ResponseEntity<List<Pizza>> getIndex(@RequestParam(required = false) String p) {
        List<Pizza> pizzas = p == null
                ? pizzaService.findAll() // Ottieni tutte le pizze se il parametro 'p' non è specificato
                : pizzaService.findByName(p); // Ottieni pizze per nome se il parametro 'p' è specificato

        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    // Utilizzo l'aggiunta di id nella rota generale per restituire la Show di una singola pizza
    @GetMapping("{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable int id) {

        Pizza pizza = pizzaService.findById(id);

        if (pizza == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Restituisci 404 se la pizza non esiste

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    // Metodo POST per creare una pizza nuova dove passo determinati parametri.
    @PostMapping()
    public ResponseEntity<Pizza> putPizza(@RequestBody Pizza pizza) {
        pizzaService.save(pizza); // Salva una nuova pizza
        return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
    }

    // Metodo PUT per aggiornare una pizza esistente
    @PutMapping("{id}")
    public ResponseEntity<Pizza> updatePizza(
            @PathVariable int id,
            @RequestBody Pizza newPizza) {

        Pizza pizza = pizzaService.findById(id);

        pizza.setName(newPizza.getName());
        pizza.setDescription(newPizza.getDescription());
        pizza.setFoto(newPizza.getFoto());
        pizza.setPrice(newPizza.getPrice());
        pizza.setIngredients(newPizza.getIngredients());

        pizzaService.save(pizza); // Aggiorna la pizza esistente

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
    
    @DeleteMapping("{id}")
	public ResponseEntity<Pizza> deletePizza(
			@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		pizzaService.delete(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK); 
	}
}

