package cl.springmachine.api.pokemon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.springmachine.api.pokemon.model.Pokemon;
import cl.springmachine.api.pokemon.repository.PokemonRepository;
import cl.springmachine.api.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

	private final PokemonRepository pokemonRepository;

	@Override
	public List<Pokemon> getAllByType(String type) {
		return pokemonRepository.findAllByType(type);
	}

	@Override
	public Pokemon getById(Integer id) {
		Optional<Pokemon> optional = pokemonRepository.findById(id);
		return optional.orElse(null);
	}

	@Override
	@Transactional
	public Integer save(Pokemon pokemon) {
		return pokemonRepository.save(pokemon).getId();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		pokemonRepository.deleteById(id);

	}

}
