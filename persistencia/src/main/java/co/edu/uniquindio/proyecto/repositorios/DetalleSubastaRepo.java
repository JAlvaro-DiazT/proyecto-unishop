package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleSubasta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Repositorio DetalleSubastaRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (DetalleSubasta) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla DetalleSubasta.
 */
@Repository
public interface DetalleSubastaRepo extends JpaRepository<DetalleSubasta, Integer> {

}
