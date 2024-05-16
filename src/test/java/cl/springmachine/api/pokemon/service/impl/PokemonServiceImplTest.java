package cl.springmachine.api.pokemon.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.springmachine.api.pokemon.exception.CustomException;
import cl.springmachine.api.pokemon.model.PokemonDto;
import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.repository.PokemonRepository;
import cl.springmachine.api.pokemon.service.ExternalService;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

	@Mock
	private ExternalService externalService;

	@Mock
	private PokemonRepository pokemonRepository;

	@InjectMocks
	private PokemonServiceImpl pokemonService;

	@Test
	void testGetAllByType() {
		String type = "fire";
		List<PokemonDto> dtoList = new ArrayList<>();
		dtoList.add(PokemonDto.builder().id(1).name("charmander").type("fire").build());
		dtoList.add(PokemonDto.builder().id(2).name("vulpix").type("fire").build());

		List<PokemonEntity> entityList = new ArrayList<>();
		entityList.add(PokemonEntity.builder().id(1).name("charmander").type("fire").build());
		entityList.add(PokemonEntity.builder().id(2).name("vulpix").type("fire").build());

		Mockito.when(pokemonRepository.findAllByType(Mockito.anyString())).thenReturn(entityList);
		List<PokemonDto> response = pokemonService.getAllByType(type);

		Assertions.assertEquals(response, dtoList);
		Assertions.assertEquals(2, response.size());
	}

	@Test
	void testGetById() {
		PokemonEntity pokemonEntity = PokemonEntity.builder().id(1).name("charmander").type("fire").build();

		PokemonDto pokemonDto = PokemonDto.builder().id(1).name("charmander").type("fire").build();

		Mockito.when(pokemonRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pokemonEntity));
		PokemonDto response = pokemonService.getById(1);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(pokemonDto, response);
	}

	@Test
	void testSave() throws CustomException {

		String name = "charmander";
		PokemonEntity pokemonEntity = PokemonEntity.builder().id(4).name("charmander").type("fire").build();
		PokemonDto pokemonDto = PokemonDto.builder().id(4).name("charmander").type("fire").build();

		Mockito.when(pokemonRepository.save(Mockito.any(PokemonEntity.class))).thenReturn(pokemonEntity);
		Mockito.when(externalService.findPokemon(name)).thenReturn(pokemonDto);

		Integer id = pokemonService.save(name);

		Assertions.assertNotNull(id);
		Assertions.assertEquals(pokemonEntity.getId(), id);
	}

	@Test
	void testDelete() {
		Integer id = 1;

		pokemonService.delete(id);

		verify(pokemonRepository, times(1)).deleteById(id);
	}

}
