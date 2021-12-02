package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Subasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Repositorio SubastaRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (Subasta) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla Subasta.
 */
@Repository
public interface SubastaRepo extends JpaRepository<Subasta, Integer> {

}
