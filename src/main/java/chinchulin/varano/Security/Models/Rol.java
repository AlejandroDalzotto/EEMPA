package chinchulin.varano.Security.Models;

import chinchulin.varano.Security.Utils.RolName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Aqui es importante decirle a Spring. que RolNombre es un Enums de tipo de
     * Cadena. (revisar Enums.RolNombre.java)
     * por eso usamos la siguiente anotacion.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private RolName rolName;

    public Rol(@NotNull RolName rolName) {
        this.rolName = rolName;
    }


}
