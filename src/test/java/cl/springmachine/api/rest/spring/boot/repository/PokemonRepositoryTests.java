package cl.springmachine.api.rest.spring.boot.repository;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PokemonRepositoryTests {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    void save() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon saved = pokemonRepository.save(pokemon);

        Assertions.assertNotNull(saved);
        Assertions.assertTrue(saved.getId() > 0);

    }

    @Test
    void findAll() {
        Pokemon first = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon second = Pokemon.builder()
                .name("gloom")
                .type("grass")
                .build();

        pokemonRepository.save(first);
        pokemonRepository.save(second);

        List<Pokemon> list = pokemonRepository.findAll();

        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

    }

    @Test
    void findById() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon saved = pokemonRepository.save(pokemon);

        Optional<Pokemon> optional = pokemonRepository.findById(saved.getId());

        Assertions.assertTrue(optional.isPresent());
    }

    @Test
    void delete() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon saved = pokemonRepository.save(pokemon);
        pokemonRepository.deleteById(saved.getId());
        Optional<Pokemon> optional = pokemonRepository.findById(saved.getId());

        Assertions.assertTrue(optional.isEmpty());

    }

    @Test
    void findAllByType() {
        Pokemon first = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon second = Pokemon.builder()
                .name("raichu")
                .type("electric")
                .build();

        pokemonRepository.save(first);
        pokemonRepository.save(second);

        List<Pokemon> list = pokemonRepository.findAllByType("electric");

        Assertions.assertEquals(2, list.size());

    }


}
