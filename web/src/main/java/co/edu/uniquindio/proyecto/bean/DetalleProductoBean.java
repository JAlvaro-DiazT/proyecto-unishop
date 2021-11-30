package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class DetalleProductoBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Getter @Setter
    private Producto producto;

    @Getter @Setter
    private Comentario nuevoComentario;

    @Getter @Setter
    private List<Comentario> comentarios;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar(){
        nuevoComentario = new Comentario();
        if(codigoProducto!=null && !codigoProducto.isEmpty()){
            Integer codigo = Integer.parseInt(codigoProducto);
            producto = productoServicio.obtenerProducto( codigo );
            this.comentarios = producto.getMiComentario();
        }
    }

    public void crearComentario(){

        try {
            if(usuarioSesion!=null){
                nuevoComentario.setMiProducto(producto);
                nuevoComentario.setMiUsuario( usuarioSesion );
                productoServicio.comentarProducto(nuevoComentario);

                this.comentarios.add(nuevoComentario);
                nuevoComentario = new Comentario();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
