package cl.springmachine.api.pokemon.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.springmachine.api.pokemon.exception.CustomException;
import cl.springmachine.api.pokemon.model.ExternalPokemonDto;
import cl.springmachine.api.pokemon.model.PokemonDto;
import cl.springmachine.api.pokemon.service.ExternalService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

	private final RestTemplate restTemplate;

	@Override
	public PokemonDto findPokemon(String name) throws CustomException {
		String url = "https://pokeapi.co/api/v2/pokemon/" + name;

		try {
			ResponseEntity<ExternalPokemonDto> responseEntity = restTemplate.getForEntity(url,
					ExternalPokemonDto.class);

			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ExternalPokemonDto externalPokemonDto = responseEntity.getBody();

				if (externalPokemonDto != null) {
					return PokemonDto.builder().id(externalPokemonDto.getId()).name(externalPokemonDto.getName())
							.type(externalPokemonDto.getTypes().stream().findFirst()
									.map(pokemonType -> pokemonType.getType().getName()).orElseThrow())
							.build();
				} else {
					throw new CustomException("Response Null");
				}

			} else {
				throw new CustomException("Error API: " + responseEntity.getStatusCode().value());
			}

		} catch (Exception ex) {
			throw new CustomException("Error data: " + ex.getMessage());
		}

	}

}
