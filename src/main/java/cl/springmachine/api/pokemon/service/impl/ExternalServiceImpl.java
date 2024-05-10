package cl.springmachine.api.pokemon.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.springmachine.api.pokemon.model.ExternalPokemonDto;
import cl.springmachine.api.pokemon.model.PokemonDto;
import cl.springmachine.api.pokemon.service.ExternalService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

	private final RestTemplate restTemplate;

	@Override
	public PokemonDto findPokemon(String name) {
		String url = "https://pokeapi.co/api/v2/pokemon/" + name;
		ExternalPokemonDto externalPokemonDto = restTemplate.getForObject(url, ExternalPokemonDto.class);

		return PokemonDto.builder().id(externalPokemonDto.getId()).name(externalPokemonDto.getName())
				.type(externalPokemonDto.getTypes().stream().findFirst()
						.map(pokemonType -> pokemonType.getType().getName()).orElseThrow())
				.build();
	}

}
