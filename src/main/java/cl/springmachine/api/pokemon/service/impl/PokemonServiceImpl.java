package cl.springmachine.api.pokemon.service.impl;

import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.repository.PokemonRepository;
import cl.springmachine.api.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Override
    public List<PokemonEntity> getAllByType(String type) {
        return pokemonRepository.findAllByType(type);
    }

    @Override
    public PokemonEntity getById(Integer id) {
        Optional<PokemonEntity> optional = pokemonRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    @Transactional
    public Integer save(PokemonEntity pokemonEntity) {
        return pokemonRepository.save(pokemonEntity).getId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pokemonRepository.deleteById(id);

    }

}
