package co.edu.uniquindio.proyecto.entidades;

import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
public class Categoria {
    @Id
    private int codigo;
}
