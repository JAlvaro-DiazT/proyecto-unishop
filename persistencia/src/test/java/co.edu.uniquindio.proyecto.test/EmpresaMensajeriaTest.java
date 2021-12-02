package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.EmpresaMensajeria;
import co.edu.uniquindio.proyecto.entidades.Seguro;
import co.edu.uniquindio.proyecto.repositorios.EmpresaMensajeriaRepo;
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
    Actualizar y Listar de la entidad EmpresaMensajeria.

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
public class EmpresaMensajeriaTest {
    @Autowired
    private EmpresaMensajeriaRepo empresaMensajeriaRepo;


    @Test // programa de tipo test para ingresar una empresa de mensajeria
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest() {
        EmpresaMensajeria empresaMensajeria=  new EmpresaMensajeria(2222,"TCC","3129875385");

        EmpresaMensajeria empresaMsjGuardada= empresaMensajeriaRepo.save(empresaMensajeria);
        Assertions.assertNotNull(empresaMsjGuardada);
    }

    @Test // programa de tipo test para eliminar una empresa de mensajeria
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest() {

        //borramos la empresa de mensajeria buscando por codigo
        empresaMensajeriaRepo.deleteById(2000);

        EmpresaMensajeria empresaMensajeriaBuscada = empresaMensajeriaRepo.findById(2000).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(empresaMensajeriaBuscada);
    }

    @Test // programa de tipo test para actualizar una empresa de mensajeria
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest() {

        EmpresaMensajeria empresaMensajeria = empresaMensajeriaRepo.findById(2002).orElse(null);
        empresaMensajeria.setTelefono("3208473628");
        //Se guarda la modificación
        empresaMensajeriaRepo.save(empresaMensajeria);

        EmpresaMensajeria empresaMsjBuscado = empresaMensajeriaRepo.findById(2002).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("3208473628",empresaMsjBuscado.getTelefono());

    }

    @Test // programa de tipo test para listar las empresas de mensajeria creadas
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest() {

        List<EmpresaMensajeria> empresas =empresaMensajeriaRepo.findAll();
        empresas.forEach(EmpresaMensajeria -> System.out.println(EmpresaMensajeria));
    }
}
