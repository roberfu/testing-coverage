package cl.springmachine.api.pokemon.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExternalPokemonDto {

    private Integer id;

    private String name;

    List<PokemonType> types;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PokemonType {

        private Integer slot;
        private Type type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Type {

        private String name;
    }

}
