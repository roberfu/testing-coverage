package cl.springmachine.api.pokemon.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExternalPokemonDto {

    private Integer id;

    private String name;

    List<PokemonType> types;

    @Data
    @Builder
    public static class PokemonType {

        private Integer slot;
        private Type type;
    }

    @Data
    @Builder
    public static class Type {

        private String name;
    }

}
