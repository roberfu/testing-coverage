package cl.springmachine.api.rest.spring.boot.service.impl;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import cl.springmachine.api.rest.spring.boot.repository.PokemonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class PokemonServiceImplTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    void savePokemon() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon saved = Pokemon.builder()
                .id(1)
                .name("pikachu")
                .type("electric")
                .build();

        Mockito.when(pokemonRepository.save(pokemon)).thenReturn(saved);

        Integer id = pokemonService.savePokemon(pokemon);

        Assertions.assertNotNull(id);
        Assertions.assertEquals(saved.getId(), id);
    }

    @Test
    void getPokemon() {
        Pokemon pokemon = Pokemon.builder()
                .id(1)
                .name("pikachu")
                .type("electric")
                .build();

        Mockito.when(pokemonRepository.findById(1)).thenReturn(Optional.ofNullable(pokemon));

        Pokemon found = pokemonService.getPokemon(1);

        Assertions.assertNotNull(found);
        Assertions.assertEquals(found, pokemon);
    }
}
