package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;

import java.util.List;

public interface CategoriaServicio {

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id) throws Exception;
}
