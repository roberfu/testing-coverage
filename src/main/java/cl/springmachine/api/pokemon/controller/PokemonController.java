package cl.springmachine.api.pokemon.controller;

import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping()
    ResponseEntity<List<PokemonEntity>> getAllByType(@RequestParam String type) {
        return new ResponseEntity<>(pokemonService.getAllByType(type), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<PokemonEntity> getById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(pokemonService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{name}")
    ResponseEntity<Map<String, Integer>> save(@PathVariable String name) {
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