package cl.springmachine.api.pokemon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.springmachine.api.pokemon.model.Pokemon;
import cl.springmachine.api.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/pokemon")
@RequiredArgsConstructor
public class PokemonController {

	private final PokemonService pokemonService;

	@GetMapping()
	ResponseEntity<List<Pokemon>> getAllByType(@RequestParam String type) {
		return new ResponseEntity<>(pokemonService.getAllByType(type), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	ResponseEntity<Pokemon> getById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(pokemonService.getById(id), HttpStatus.OK);
	}

	@PostMapping()
	ResponseEntity<Map<String, Integer>> save(@RequestBody Pokemon pokemon) {
		Map<String, Integer> response = new HashMap<>();
		response.put("id", pokemonService.save(pokemon));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	ResponseEntity<Pokemon> delete(@PathVariable(value = "id") Integer id) {
		pokemonService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}