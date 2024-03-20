package cl.springmachine.api.pokemon.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import cl.springmachine.api.pokemon.model.Pokemon;
import cl.springmachine.api.pokemon.repository.PokemonRepository;

class PokemonServiceImplTest {

	@Mock
	private PokemonRepository pokemonRepository;

	@InjectMocks
	private PokemonServiceImpl pokemonService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllByType() {
		String type = "fire";
		List<Pokemon> list = new ArrayList<>();

		list.add(Pokemon.builder().id(1).name("charmander").type("fire").build());
		list.add(Pokemon.builder().id(2).name("vulpix").type("fire").build());

		Mockito.when(pokemonRepository.findAllByType(type)).thenReturn(list);

		List<Pokemon> response = pokemonService.getAllByType(type);

		Assertions.assertEquals(response, list);
		Assertions.assertEquals(2, response.size());
	}

	@Test
	void testGetById() {
		Pokemon pokemon = Pokemon.builder().id(1).name("charmander").type("fire").build();

		Mockito.when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		Pokemon response = pokemonService.getById(1);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(pokemon, response);
	}

	@Test
	void testSave() {
		Pokemon pokemon = Pokemon.builder().id(1).name("charmander").type("fire").build();

		Mockito.when(pokemonRepository.save(Pokemon.builder().name("charmander").type("fire").build()))
				.thenReturn(pokemon);

		Integer id = pokemonService.save(Pokemon.builder().name("charmander").type("fire").build());

		Assertions.assertNotNull(id);
		Assertions.assertEquals(pokemon.getId(), id);
	}

	@Test
	void testDelete() {
		Integer id = 1;

		pokemonService.delete(id);

		verify(pokemonRepository, times(1)).deleteById(id);
	}

}
