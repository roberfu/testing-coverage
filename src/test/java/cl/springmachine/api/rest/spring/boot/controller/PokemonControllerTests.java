package cl.springmachine.api.rest.spring.boot.controller;

import cl.springmachine.api.rest.spring.boot.model.Pokemon;
import cl.springmachine.api.rest.spring.boot.service.PokemonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class PokemonControllerTests {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pokemonController).build();
    }

    private MockMvc mockMvc;

    @Mock
    private PokemonService pokemonService;

    @InjectMocks
    private PokemonController pokemonController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getPokemon() throws Exception {
        Integer id = 1;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("pikachu")
                .type("electric")
                .build();
        Mockito.when(pokemonService.getPokemon(id)).thenReturn(pokemon);

        ResultActions response = mockMvc.perform(get("/api/pokemons/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(pokemon.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type", CoreMatchers.is(pokemon.getType())));
    }

    @Test
    void savePokemon() throws Exception {
        Integer id = 1;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("pikachu")
                .type("electric")
                .build();
        Mockito.when(pokemonService.savePokemon(pokemon)).thenReturn(id);

        ResultActions response = mockMvc.perform(post("/api/pokemons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pokemon)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(id.toString())));
    }
}
