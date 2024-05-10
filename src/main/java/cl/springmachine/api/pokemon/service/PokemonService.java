package cl.springmachine.api.pokemon.service;

import java.util.List;

import cl.springmachine.api.pokemon.exception.CustomException;
import cl.springmachine.api.pokemon.model.PokemonDto;

public interface PokemonService {

	List<PokemonDto> getAllByType(String type);

	PokemonDto getById(Integer id);

	Integer save(String name) throws CustomException;

	void delete(Integer id);

}
