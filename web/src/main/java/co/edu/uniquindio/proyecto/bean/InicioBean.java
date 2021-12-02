package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Getter @Setter
    private List<Producto> productos;

    @Getter @Setter
    private Categoria categoria;

    @PostConstruct
    public void inicializar(){
        try {
            this.categoria = categoriaServicio.obtenerCategoria(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.productos = productoServicio.listarTodosProductos();
    }

    public String irADetaller(Integer id){
        return "detalleProducto?faces-redirect=true&amp;producto="+id;
    }

}
