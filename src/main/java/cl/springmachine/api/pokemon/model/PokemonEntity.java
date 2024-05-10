package cl.springmachine.api.pokemon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    private Integer id;

    private String name;

    private String type;
}
