package cl.springmachine.api.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.springmachine.api.pokemon.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

	List<Pokemon> findAllByType(String type);

}