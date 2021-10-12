package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Administrador extends Persona implements Serializable {

    public Administrador(Integer codigo, String nombre, String email, String password) {
        super(codigo, nombre, email, password);
    }
}
