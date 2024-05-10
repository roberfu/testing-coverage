package cl.springmachine.api.pokemon.service;

import cl.springmachine.api.pokemon.model.PokemonDto;

public interface ExternalService {

	PokemonDto findPokemon(String name);

}
