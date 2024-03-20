package cl.springmachine.api.pokemon.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PokemonDtoTest {

	@Test
	void test() {
		PokemonDto first = new PokemonDto(1, "charmander", "fire");
		PokemonDto second = new PokemonDto(2, "charizard", "fire");
		PokemonDto repeatFirst = new PokemonDto(1, "charmander", "fire");

		Assertions.assertEquals(1, first.id());
		Assertions.assertEquals("charmander", first.name());
		Assertions.assertEquals("fire", first.type());
		Assertions.assertEquals(first, repeatFirst);
		Assertions.assertNotEquals(first, second);
	}

}
