package co.edu.uniquindio.proyecto.proyecciones;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.rest.core.config.Projection;

import java.util.Map;

@Projection(
        name="usuarioBase",
        types = Usuario.class
)
public interface UsuarioBase {

    String getNombre();
    String getEmail();
    String getUsername();
    Map<String, String> getTelefonos();

}
