package cl.springmachine.api.pokemon.service.impl;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import cl.springmachine.api.pokemon.model.ExternalPokemonDto;
import cl.springmachine.api.pokemon.model.PokemonDto;

@ExtendWith(MockitoExtension.class)
class ExternalServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private ExternalServiceImpl externalService;

	@Test
	void test_findPokemon_ReturnsPokemonDto() {
		String name = "pikachu";
		ExternalPokemonDto externalPokemonDto = ExternalPokemonDto.builder().id(25).name("pikachu")
				.types(List.of(ExternalPokemonDto.PokemonType.builder().slot(1)
						.type(ExternalPokemonDto.Type.builder().name("electric").build()).build()))
				.build();

		Mockito.when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/" + name, ExternalPokemonDto.class))
				.thenReturn(externalPokemonDto);

		PokemonDto pokemonDto = externalService.findPokemon(name);

		Assertions.assertNotNull(pokemonDto);
		Assertions.assertNotNull(externalPokemonDto);
		Assertions.assertEquals(25, pokemonDto.getId());
		Assertions.assertEquals(name, pokemonDto.getName());
	}

}