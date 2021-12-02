package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.MensajeRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
    Actualizar y Listar de la entidad Mensaje.

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
public class MensajeTest {

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Test // programa de tipo test para ingresar un mensaje
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {

        //Obtener un chat ya registrado
        Chat chat =  chatRepo.findById(802).orElse(null);

        Mensaje mensaje=  new Mensaje(910,"Buenas tardes, ¿cuando llega mi producto?","Carolina Cardona", LocalDateTime.now(),chat);

        Mensaje mensajeGuardado= mensajeRepo.save(mensaje);
        Assertions.assertNotNull(mensajeGuardado);
    }

    @Test // programa de tipo test para eliminar un mensaje
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos el mensaje buscando por codigo
        mensajeRepo.deleteById(900);

        Mensaje mensajeBuscado = mensajeRepo.findById(900).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(mensajeBuscado);
    }

    @Test // programa de tipo test para actualizar un mensaje
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        Mensaje mensaje = mensajeRepo.findById(902).orElse(null);
        mensaje.setEmisor("Duvan Marin");
        //Se guarda la modificación
        mensajeRepo.save(mensaje);

        Mensaje mensajeBuscado = mensajeRepo.findById(902).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("Duvan Marin",mensajeBuscado.getEmisor());

    }

    @Test // programa de tipo test para listar los mensajes
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<Mensaje> mensajes =mensajeRepo.findAll();
        mensajes.forEach(Mensaje -> System.out.println(Mensaje));
    }
}
