package cl.springmachine.api.pokemon.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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

		try {
			ResponseEntity<ExternalPokemonDto> responseEntity = restTemplate.getForEntity(url,
					ExternalPokemonDto.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ExternalPokemonDto externalPokemonDto = responseEntity.getBody();

				return PokemonDto.builder().id(externalPokemonDto.getId()).name(externalPokemonDto.getName())
						.type(externalPokemonDto.getTypes().stream().findFirst()
								.map(pokemonType -> pokemonType.getType().getName()).orElseThrow())
						.build();
			} else {
				throw new RuntimeException("Error API: " + responseEntity.getStatusCode().value());
			}

		} catch (HttpClientErrorException ex) {
			throw new RuntimeException(
					"Error HTTP Client: " + ex.getStatusCode() + " - " + ex.getResponseBodyAsString());
		} catch (Exception ex) {
			throw new RuntimeException("Error data: " + ex.getMessage());
		}

	}

}
