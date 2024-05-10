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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.springmachine.api.pokemon.exception.CustomException;
import cl.springmachine.api.pokemon.model.PokemonDto;
import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.service.PokemonService;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

	private final PokemonService pokemonService;

	public PokemonController(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}

	@GetMapping()
	ResponseEntity<List<PokemonDto>> getAllByType(@RequestParam String type) {
		return new ResponseEntity<>(pokemonService.getAllByType(type), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	ResponseEntity<PokemonDto> getById(@PathVariable(value = "id") Integer id) {
		return new ResponseEntity<>(pokemonService.getById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/{name}")
	ResponseEntity<Map<String, Integer>> save(@PathVariable String name) throws CustomException {
		Map<String, Integer> response = new HashMap<>();
		response.put("id", pokemonService.save(name));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	ResponseEntity<PokemonEntity> delete(@PathVariable(value = "id") Integer id) {
		pokemonService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}