package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Seguro;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.SeguroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

/*
    Clase por medio de la cual se van a realizar las pruebas de las funciones de: Registrar, Eliminar,
    Actualizar y Listar de la entidad Seguro.

    La anotacion @DataJpaTest indica que es una clase para probar datos
    La anotacion @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) para que
    los datos de la prueba se guarde en la base de datos
    La anotacion @Autowired inicializa las variables que representan componentes de SpringBoot
    La anotacion @Test permite ejecutar las pruebas
    La anotacion @Sql("classpath:datosUnishop.sql") por medio del cual se realiza la conexion a los
    recursos del archivo Sql
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base
public class SeguroTest {
    @Autowired
    private SeguroRepo seguroRepo;


    @Test // programa de tipo test para ingresar un seguro
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {


        Seguro seguro=  new Seguro(1111,"Se cubre el 20% del valor del producto en caso de robo",LocalDateTime.now(),LocalDateTime.of(2022,10,31,23,59),40000);

        Seguro seguroGuardado= seguroRepo.save(seguro);
        Assertions.assertNotNull(seguroGuardado);
    }

    @Test // programa de tipo test para eliminar un seguro
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos el seguro buscando por codigo
        seguroRepo.deleteById(1000);

        Seguro seguroBuscado = seguroRepo.findById(1000).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(seguroBuscado);
    }

    @Test // programa de tipo test para actualizar un seguro
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Seguro seguro = seguroRepo.findById(1002).orElse(null);
        seguro.setValor(70000);
        //Se guarda la modificación
        seguroRepo.save(seguro);

        Seguro seguroBuscado = seguroRepo.findById(1002).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals(70000,seguroBuscado.getValor());

    }

    @Test // programa de tipo test para listar los seguros creados
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Seguro> seguros =seguroRepo.findAll();
        seguros.forEach(Seguro -> System.out.println(Seguro));
    }
}
