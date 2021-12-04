package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public interface ProductoServicio {

    Producto publicarProducto(Producto p) throws Exception;

    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException;

    List <Producto> listarProductos(Categoria categoria);

    List <Producto> listarTodosProductos();

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception;

    void comprarProductos(Compra compra) throws Exception;

    List<Producto> buscarProductos(String nombre, String[] filtros);

    List<Producto> listarProducto(String codigoUsuario) throws Exception;
    Compra comprarProductos(ArrayList<ProductoCarrito> productos,Compra compra) throws Exception;

    List<Producto> listarMisProductos(Usuario usuarioSesion) throws Exception;

    Subasta agregarSubasta(Subasta subasta) throws Exception;
}
