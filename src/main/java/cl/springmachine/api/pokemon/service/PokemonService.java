package cl.springmachine.api.pokemon.service;

import java.util.List;

import cl.springmachine.api.pokemon.model.PokemonEntity;

public interface PokemonService {

	List<PokemonEntity> getAllByType(String type);

	PokemonEntity getById(Integer id);

	Integer save(String name);

	void delete(Integer id);

}
