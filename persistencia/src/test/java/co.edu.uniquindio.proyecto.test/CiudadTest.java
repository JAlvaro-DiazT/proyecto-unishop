package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base
public class CiudadTest {
    @Autowired
   private CiudadRepo ciudadRepo;

    @Test // programa de tipo test para ingresar una ciudad
    @Sql("classpath:datosUnishop.sql")
    public void registrarTest()
    {
        Ciudad ciudad=  new Ciudad(5,"Armenia");
        Ciudad ciudadGuardada= ciudadRepo.save(ciudad);
        Assertions.assertNotNull(ciudadGuardada);
    }

    @Test // programa de tipo test para eliminar una ciudad
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest()
    {
        //borramos la ciudad buscando por codigo
        ciudadRepo.deleteById(2);
        //Se busca la ciudad para verificar si lo borro

        Ciudad ciudadBuscada = ciudadRepo.findById(2).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(ciudadBuscada);
    }

    @Test // programa de tipo test para actualizar una ciudad
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest()
    {
        Ciudad ciudadGuardada = ciudadRepo.findById(3).orElse(null);
        ciudadGuardada.setNombre("Pereira");
        //Se guarda la modificación
        ciudadRepo.save(ciudadGuardada);

        //Busco la ciudad
        Ciudad ciudadBuscada = ciudadRepo.findById(3).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("Pereira",ciudadBuscada.getNombre());

    }


    @Test // programa de tipo test para listar las ciudades creadas
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest()
    {
        List<Ciudad> ciudades =ciudadRepo.findAll();
        ciudades.forEach(ciudad -> System.out.println(ciudad));

    }
}