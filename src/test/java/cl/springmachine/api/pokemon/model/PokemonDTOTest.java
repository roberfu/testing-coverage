package cl.springmachine.api.pokemon.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonDtoTest {


    @Test
    void test() {
        PokemonDto first = new PokemonDto(1, "charmander", "fire");
        PokemonDto second = new PokemonDto(2, "vulpix", "fire");
        PokemonDto repeatFirst = new PokemonDto(1, "charmander", "fire");

        Assertions.assertEquals(1, first.getId());
        Assertions.assertEquals(first, repeatFirst);
        Assertions.assertNotEquals(first, second);
    }
}