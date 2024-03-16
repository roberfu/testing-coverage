package cl.springmachine.api.rest.spring.boot.controller;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import cl.springmachine.api.rest.spring.boot.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Pokemon> getPokemon(@PathVariable Integer id) {
        return new ResponseEntity<>(pokemonService.getPokemon(id), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<Map<String, String>> getPokemon(@RequestBody Pokemon pokemon) {
        Map<String, String> response = new HashMap<>();
        response.put("id", pokemonService.savePokemon(pokemon).toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
