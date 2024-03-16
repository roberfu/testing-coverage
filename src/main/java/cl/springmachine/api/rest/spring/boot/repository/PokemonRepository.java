package cl.springmachine.api.rest.spring.boot.repository;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    List<Pokemon> findAllByType(String type);
}
