package cl.springmachine.api.rest.spring.boot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonTests {

    @Test
    void equals() {
        Pokemon first = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon second = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();


        Assertions.assertNotNull(first);
        Assertions.assertNotNull(second);
        Assertions.assertEquals(first, second);
    }
}
