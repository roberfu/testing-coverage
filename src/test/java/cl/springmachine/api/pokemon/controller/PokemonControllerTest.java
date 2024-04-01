package cl.springmachine.api.pokemon.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.springmachine.api.pokemon.model.Pokemon;
import cl.springmachine.api.pokemon.service.PokemonService;

class PokemonControllerTest {

	@Mock
	private PokemonService pokemonService;

	@InjectMocks
	private PokemonController pokemonController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllByType() {
		String type = "fire";
		List<Pokemon> list = new ArrayList<>();
		list.add(Pokemon.builder().id(1).name("charmander").type("fire").build());
		list.add(Pokemon.builder().id(2).name("vulpix").type("fire").build());

		Mockito.when(pokemonService.getAllByType(type)).thenReturn(list);
		ResponseEntity<List<Pokemon>> response = pokemonController.getAllByType(type);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(list, response.getBody());
	}

	@Test
	void testGetById() {
		Integer id = 1;
		Pokemon pokemon = Pokemon.builder().id(id).name("charmander").type("fire").build();

		Mockito.when(pokemonService.getById(id)).thenReturn(pokemon);
		ResponseEntity<Pokemon> response = pokemonController.getById(id);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(pokemon, response.getBody());

	}

	@Test
	void testSave() {
		Pokemon pokemon = Pokemon.builder().id(1).name("charmander").type("fire").build();
		Mockito.when(pokemonService.save(pokemon)).thenReturn(1);

		ResponseEntity<Map<String, Integer>> response = pokemonController.save(pokemon);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(1, response.getBody().get("id"));

	}

	@Test
	void testDelete() {
		Integer id = 1;

		ResponseEntity<Pokemon> response = pokemonController.delete(id);

		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(pokemonService, times(1)).delete(id);

	}
}
