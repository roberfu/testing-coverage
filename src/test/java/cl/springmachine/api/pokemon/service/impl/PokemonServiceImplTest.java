package cl.springmachine.api.pokemon.service.impl;

import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.repository.PokemonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Test
    void testGetAllByType() {
        String type = "fire";
        List<PokemonEntity> list = new ArrayList<>();
        list.add(PokemonEntity.builder().id(1).name("charmander").type("fire").build());
        list.add(PokemonEntity.builder().id(2).name("vulpix").type("fire").build());

        when(pokemonRepository.findAllByType(type)).thenReturn(list);
        List<PokemonEntity> response = pokemonService.getAllByType(type);

        Assertions.assertEquals(response, list);
        Assertions.assertEquals(2, response.size());
    }

    @Test
    void testGetById() {
        PokemonEntity pokemonEntity = PokemonEntity.builder().id(1).name("charmander").type("fire").build();

        when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemonEntity));
        PokemonEntity response = pokemonService.getById(1);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(pokemonEntity, response);
    }

    @Test
    void testSave() {
        PokemonEntity pokemonEntity = PokemonEntity.builder().id(1).name("charmander").type("fire").build();

        when(pokemonRepository.save(PokemonEntity.builder().name("charmander").type("fire").build()))
                .thenReturn(pokemonEntity);
        Integer id = pokemonService.save(PokemonEntity.builder().name("charmander").type("fire").build());

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
