package cl.springmachine.api.pokemon.service;

import java.util.List;

import cl.springmachine.api.pokemon.model.Pokemon;

public interface PokemonService {

	List<Pokemon> getAllByType(String type);

	Pokemon getById(Integer id);

	Integer save(Pokemon pokemon);

	void delete(Integer id);

}
