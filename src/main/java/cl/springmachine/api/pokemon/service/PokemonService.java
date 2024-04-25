package cl.springmachine.api.pokemon.service;

import cl.springmachine.api.pokemon.model.PokemonEntity;

import java.util.List;

public interface PokemonService {

    List<PokemonEntity> getAllByType(String type);

    PokemonEntity getById(Integer id);

    Integer save(PokemonEntity pokemonEntity);

    void delete(Integer id);

}
