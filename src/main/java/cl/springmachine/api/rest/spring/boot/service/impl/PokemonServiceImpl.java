package cl.springmachine.api.rest.spring.boot.service.impl;


import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import cl.springmachine.api.rest.spring.boot.repository.PokemonRepository;
import cl.springmachine.api.rest.spring.boot.service.PokemonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    @Override
    public Pokemon getPokemon(Integer id) {
        Optional<Pokemon> optional = pokemonRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Integer savePokemon(Pokemon pokemon) {
        Pokemon saved = pokemonRepository.save(pokemon);
        return saved.getId();
    }
}
