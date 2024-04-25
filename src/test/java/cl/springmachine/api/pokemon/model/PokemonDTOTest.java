package cl.springmachine.api.pokemon.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonDTOTest {


    @Test
    void test() {
        PokemonDTO first = new PokemonDTO(1, "charmander", "fire");
        PokemonDTO second = new PokemonDTO(2, "vulpix", "fire");
        PokemonDTO repeatFirst = new PokemonDTO(1, "charmander", "fire");

        Assertions.assertEquals(1, first.id());
        Assertions.assertEquals(first, repeatFirst);
        Assertions.assertNotEquals(first, second);
    }
}