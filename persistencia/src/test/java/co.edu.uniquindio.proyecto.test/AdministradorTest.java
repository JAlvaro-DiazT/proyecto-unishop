package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.aspectj.apache.bcel.util.ClassPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para que se guarde en la base
public class AdministradorTest
{
    @Autowired
    private AdministradorRepo administradorRepo;

    @Test // programa de tipo test para ingresar un administrador
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest()
    {
        Administrador administrador=  new Administrador(002,"Carolina","correo@mail.com", "13332");
        Administrador adminGuardado= administradorRepo.save(administrador);
        Assertions.assertNotNull(adminGuardado);
    }

    @Test // programa de tipo test para eliminar un administrador
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest()
    {
        //borramos el usuario buscando por codigo
        administradorRepo.deleteById(003);
        //busco el administrador para verificar su lo borro

        Administrador adminBuscado = administradorRepo.findById(003).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(adminBuscado);
    }

    @Test // programa de tipo test para actualizar un administrador
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest()
    {
        Administrador administrador = administradorRepo.findById(004).orElse(null);
        administrador.setNombre("Duvan Molina");
        //Se guarda la modificación
        administradorRepo.save(administrador);

        //Busco la ciudad
        Administrador adminBuscado = administradorRepo.findById(004).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals("Duvan Molina",adminBuscado.getNombre());

    }


    @Test // programa de tipo test para listar los administradores creados
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest()
    {
        List<Administrador> administradores =administradorRepo.findAll();
        administradores.forEach(Administrador -> System.out.println(Administrador));

    }

}
