package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.EmpresaMensajeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Repositorio EmpresaMensajeriaRepo, Interface la cual extiende de JpaRepository, cuenta con dos datos:
    la tabla que hace referencia (EmpresaMensajeria) y el tipo de dato de la llave primaria de la entidad
    (Integer), de esta manera se podra realizar el respectivo CRUD en la tabla EmpresaMensajeria.
 */
@Repository
public interface EmpresaMensajeriaRepo extends JpaRepository<EmpresaMensajeria, Integer> {

}
