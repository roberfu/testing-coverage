package cl.springmachine.api.pokemon.controller;

import cl.springmachine.api.pokemon.model.PokemonEntity;
import cl.springmachine.api.pokemon.service.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    @Test
    void testGetAllByType() {
        String type = "fire";
        List<PokemonEntity> list = new ArrayList<>();
        list.add(PokemonEntity.builder().id(1).name("charmander").type("fire").build());
        list.add(PokemonEntity.builder().id(2).name("vulpix").type("fire").build());

        when(pokemonService.getAllByType(type)).thenReturn(list);
        ResponseEntity<List<PokemonEntity>> response = pokemonController.getAllByType(type);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(list, response.getBody());


    }

    @Test
    void testGetById() {
        Integer id = 1;
        PokemonEntity pokemonEntity = PokemonEntity.builder().id(id).name("charmander").type("fire").build();

        when(pokemonService.getById(id)).thenReturn(pokemonEntity);
        ResponseEntity<PokemonEntity> response = pokemonController.getById(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(pokemonEntity, response.getBody());

    }

    @Test
    void testSave() {
        PokemonEntity pokemonEntity = PokemonEntity.builder().id(1).name("charmander").type("fire").build();
        when(pokemonService.save(pokemonEntity)).thenReturn(1);

        ResponseEntity<Map<String, Integer>> response = pokemonController.save(pokemonEntity);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(1, response.getBody().get("id"));

    }

    @Test
    void testDelete() {
        Integer id = 1;

        ResponseEntity<PokemonEntity> response = pokemonController.delete(id);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pokemonService, times(1)).delete(id);

    }
}
