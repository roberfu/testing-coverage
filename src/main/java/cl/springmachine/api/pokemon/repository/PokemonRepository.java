package cl.springmachine.api.pokemon.repository;

import cl.springmachine.api.pokemon.model.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<PokemonEntity, Integer> {

    List<PokemonEntity> findAllByType(String type);

}