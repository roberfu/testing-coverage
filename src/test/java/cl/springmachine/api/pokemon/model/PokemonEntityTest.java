package cl.springmachine.api.pokemon.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonEntityTest {

    @Test
    void test() {
        PokemonEntity first = new PokemonEntity(1, "charmander", "fire");
        PokemonEntity second = new PokemonEntity(2, "charizard", "fire");
        PokemonEntity repeatFirst = new PokemonEntity(1, "charmander", "fire");
        PokemonEntity empty = new PokemonEntity();
        second.setType("dragon");

        Assertions.assertEquals(1, first.getId());
        Assertions.assertEquals("charmander", first.getName());
        Assertions.assertEquals("fire", first.getType());
        Assertions.assertEquals(first, repeatFirst);
        Assertions.assertEquals(first.hashCode(), repeatFirst.hashCode());
        Assertions.assertNotEquals(first, second);
        Assertions.assertEquals("dragon", second.getType());
        Assertions.assertNotEquals(first.hashCode(), second.hashCode());
        Assertions.assertNotNull(empty);
    }

}
