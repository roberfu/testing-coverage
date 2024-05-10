package cl.springmachine.api.pokemon.service.impl;

import cl.springmachine.api.pokemon.model.PokemonDto;
import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.repository.PokemonRepository;
import cl.springmachine.api.pokemon.service.ExternalService;
import cl.springmachine.api.pokemon.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    private final ExternalService externalService;

    @Override
    public List<PokemonDto> getAllByType(String type) {
        return pokemonRepository.findAllByType(type).stream()
                .map(t -> PokemonDto.builder().id(t.getId()).name(t.getName()).type(t.getType()).build()).toList();
    }

    @Override
    public PokemonDto getById(Integer id) {
        Optional<PokemonEntity> optional = pokemonRepository.findById(id);
        return optional.map(pokemonEntity -> PokemonDto.builder()
                .id(pokemonEntity.getId()).name(pokemonEntity.getName())
                .type(pokemonEntity.getType()).build()).orElse(null);
    }

    @Override
    @Transactional
    public Integer save(String name) {
        PokemonDto pokemonDto = externalService.findPokemon(name);
        return pokemonRepository.save(PokemonEntity.builder().id(pokemonDto.getId()).name(pokemonDto.getName())
                .type(pokemonDto.getType()).build()).getId();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pokemonRepository.deleteById(id);

    }

}
