package cl.springmachine.api.rest.spring.boot.service;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class PokemonServiceTests {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    private PokemonService pokemonService;

    @Test
    void getPokemon() {
        Integer id = 1;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("pikachu")
                .type("electric")
                .build();

        Mockito.when(pokemonService.getPokemon(id)).thenReturn(pokemon);

        Pokemon saved = pokemonService.getPokemon(id);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(pokemon, saved);
    }
}
