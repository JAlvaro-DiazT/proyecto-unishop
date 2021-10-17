package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base
public class DetalleSubastaTest {
    @Autowired
    private DetalleSubastaRepo detalleSubastaRepo;

    @Autowired
    private SubastaRepo subastaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test // programa de tipo test para ingresar el detalle de la subasta
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest()
    {
        //Obtener una subasta ya registrada
        Subasta subasta =  subastaRepo.findById(500).orElse(null);
        //Obtener un usuario  ya registrado
        Usuario usuario =  usuarioRepo.findById(500).orElse(null);

        DetalleSubasta detalleSubasta=  new DetalleSubasta(610,50000,LocalDateTime.now(),subasta,usuario);

        DetalleSubasta detalleGuardado= detalleSubastaRepo.save(detalleSubasta);
        Assertions.assertNotNull(detalleGuardado);
    }

    @Test // programa de tipo test para eliminar un detalle de una subasta
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest()
    {
        //borramos el detalle de la subasta buscando por codigo
        detalleSubastaRepo.deleteById(603);

        DetalleSubasta detalleSubasta = detalleSubastaRepo.findById(603).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(detalleSubasta);
    }

    @Test // programa de tipo test para actualizar el detalle de una subasta
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest()
    {
        DetalleSubasta detalleSubasta = detalleSubastaRepo.findById(600).orElse(null);
        detalleSubasta.setValor(30000);
        //Se guarda la modificación
        detalleSubastaRepo.save(detalleSubasta);

        DetalleSubasta detalleSubasta1 = detalleSubastaRepo.findById(600).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals(30000,detalleSubasta1.getValor());

    }

    @Test // programa de tipo test para listar los detalles de las subastas
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest()
    {
        List<DetalleSubasta> detalleSubastas =detalleSubastaRepo.findAll();
        detalleSubastas.forEach(DetalleSubasta -> System.out.println(DetalleSubasta));
    }
}
