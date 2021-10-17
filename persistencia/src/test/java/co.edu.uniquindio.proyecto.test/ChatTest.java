package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
public class ChatTest {
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ChatRepo chatRepo;

    @Test // programa de tipo test para ingresar en un chat
    @Sql("classpath:datosUnishop.sql")
    public void RegistrarTest()
    {
        //Obtener un usuario ya registrado
        Usuario usuario =  usuarioRepo.findById(101).orElse(null);

        Chat chat=  new Chat(888,usuario);

        Chat chatAlmacenado= chatRepo.save(chat);
        Assertions.assertNotNull(chatAlmacenado);
    }

    @Test // programa de tipo test para eliminar un chat
    @Sql("classpath:datosUnishop.sql")
    public void eliminarTest()
    {
        //borramos el chat buscando por codigo
        chatRepo.deleteById(800);

        Chat chatBuscado = chatRepo.findById(800).orElse(null);
        // para decir que lo que espero es un null
        Assertions.assertNull(chatBuscado);
    }

    @Test // programa de tipo test para actualizar un chat
    @Sql("classpath:datosUnishop.sql")
    public void actualizarTest()
    {
        //Obtener un usuario ya registrado
        Usuario usuario =  usuarioRepo.findById(103).orElse(null);

        Chat chat = chatRepo.findById(801).orElse(null);
        chat.setMiUsuario(usuario);
        //Se guarda la modificación
        chatRepo.save(chat);

        Chat chatBuscado = chatRepo.findById(801).orElse(null);

        //Se busca que si haya quedado en el registro el cambio
        Assertions.assertEquals(usuario,chatBuscado.getMiUsuario());

    }

    @Test // programa de tipo test para listar los chats creados
    @Sql("classpath:datosUnishop.sql")
    public void ListarTest()
    {
        List<Chat> chats =chatRepo.findAll();
        chats.forEach(Chat -> System.out.println(Chat));
    }
}
