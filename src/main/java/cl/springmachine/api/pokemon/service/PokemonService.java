package cl.springmachine.api.pokemon.service;

import java.util.List;

import cl.springmachine.api.pokemon.model.PokemonDto;

public interface PokemonService {

	List<PokemonDto> getAllByType(String type);

	PokemonDto getById(Integer id);

	Integer save(String name);

	void delete(Integer id);

}
