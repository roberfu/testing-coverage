package cl.springmachine.api.rest.spring.boot.repository;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @BeforeEach
    void setUp() {
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