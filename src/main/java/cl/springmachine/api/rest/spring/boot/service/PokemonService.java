package cl.springmachine.api.rest.spring.boot.service;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;

public interface PokemonService {

    Pokemon getPokemon(Integer id);

    Integer savePokemon(Pokemon pokemon);

}
