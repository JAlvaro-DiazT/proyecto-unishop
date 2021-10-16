package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
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




    @Test // programa de tipo test para ingresar un usuario
    @Sql("classpath:unishopBase.sql")
    public void RegistrarTest()
    {
        Administrador administrador=  new Administrador(002,"Carolina",
                "correo@mail.com","13332");
        Administrador adminGuardado= administradorRepo.save(administrador);
        Assertions.assertNotNull(adminGuardado);


    }
    @Test // programa de tipo test para ingresar un usuario
    @Sql("classpath:unishopBase.sql")
    public void eliminarTest()
    {

        Administrador administrador=  new Administrador(003,"Alvaro",
                "correo2020@mail.com","13333332");
        //mando este administrador al repositorio para que se encargue de guardarlo en la base
        administradorRepo.save(administrador);

        //borramos el usuario buscando por codigo
        administradorRepo.deleteById(003);
        //busco el administrador para verificar su lo borro

        Administrador adminBuscado = administradorRepo.findById(003).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(adminBuscado);

    }

    @Test // programa de tipo test para ingresar un usuario
    @Sql("classpath:unishopBase.sql")
    public void actualizarTest()
    {

        // A manera de prueba agrego un usuario
        Administrador administrador=  new Administrador(004,"Pepito",
                "pepito@mail.com","20015");

        //mando este administrador al repositorio para que se encargue de guardarlo en la base

        Administrador adminGuardado= administradorRepo.save(administrador);

        //modifico el nombre
        adminGuardado.setNombre("Juanito");
        //vuelvo a guardar en la base

        administradorRepo.save(adminGuardado);

        //Busco el usuario
        Administrador adminBuscado = administradorRepo.findById(004).orElse(null);

        //con la variable recien creada(adminBuscado) busco que si haya quedado en el registro el cambio
        Assertions.assertEquals("Juanito",adminBuscado.getNombre());

    }


    @Test // programa de tipo test para ingresar un usuario
    @Sql("classpath:unishopBase.sql")
    public void ListarTest()
    {
        Administrador administrador=  new Administrador(003,"Alvaro",
                "correo2020@mail.com","13333332");
        Administrador adminGuardado= administradorRepo.save(administrador);
        List<Administrador> administradorL= administradorRepo.findAll();
        System.out.println(administradorL+"ADMINISTRADORES AGREGADOS");

    }



}
