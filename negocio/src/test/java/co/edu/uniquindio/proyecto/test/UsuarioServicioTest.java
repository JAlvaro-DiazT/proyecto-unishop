package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest() {
        Usuario u = new Usuario(123, "Pepito@gmail.com", "Pepito", "1234", null, null);
        try {
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarUsuarioTest() {
        try {
            usuarioServicio.eliminarUsuario(123);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarTest() {
        List<Usuario> lista = usuarioServicio.listarUsuarios();
        lista.forEach( System.out::println );
    }

    @Test
    public void actualizarTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario(123);
            u.setPassword("1428");
            usuarioServicio.actualizarUsuario(u);
            Usuario modificado = usuarioServicio.obtenerUsuario(123);
            Assertions.assertEquals("1428", modificado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginTest() {
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("Pepito@gmail.com", "1428");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

}
