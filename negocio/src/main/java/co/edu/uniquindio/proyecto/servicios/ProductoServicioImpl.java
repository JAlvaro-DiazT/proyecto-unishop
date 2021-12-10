package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio{

    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;
    private final CompraRepo compraRepo;
    private final DetalleCompraRepo detalleCompraRepo;
    private final SubastaRepo subastaRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo, ComentarioRepo comentarioRepo, CompraRepo compraRepo, DetalleCompraRepo detalleCompraRepo, SubastaRepo subastaRepo) {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.compraRepo = compraRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.subastaRepo = subastaRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception {
        try {
            return productoRepo.save(p);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Producto actualizarProducto(Producto p) throws Exception {

        Optional<Producto> buscado = productoRepo.findById(p.getCodigo());
        if(buscado.isEmpty()) {
            throw new Exception("El Producto no existe");
        }
        return productoRepo.save(p);

    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigo);

        if(producto.isEmpty()){
            throw new Exception("El codigo del producto no existe");
        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoException {
        return productoRepo.findById(codigo).orElseThrow( () -> new ProductoNoEncontradoException("El codigo del producto no es valido"));
    }

    @Override
    public List<Producto> listarProductos(Categoria categoria) {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception {
        comentario.setFecha_comentario(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto, Usuario usuario) throws Exception {

    }

    @Override
    public void comprarProductos(Compra compra) throws Exception {

    }

    @Override
    public List<Producto> buscarProductos(String nombreProducto, String[] filtros) {

        return productoRepo.buscarProductoNombre(nombreProducto);
    }

    @Override
    public List<Producto> listarProducto(String codigoUsuario) throws Exception {
        return null;
    }


    @Override
    public Compra comprarProductos( ArrayList<ProductoCarrito> productos,Compra compra) throws Exception {
        try {

            Compra compraGuardada = compraRepo.save(compra);

            DetalleCompra dc;
            for (ProductoCarrito p: productos) {
                dc = new DetalleCompra();
                dc.setMiCompra(compraGuardada);
                dc.setPrecio_producto(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                dc.setMiProducto( productoRepo.findById(p.getId()).get() );

                Integer disponibles = productoRepo.obtenerTotalUnidadesDisponibles(p.getId());

                if(productoRepo.findById(p.getId()).get().getUnidades() <= disponibles){
                    detalleCompraRepo.save(dc);
                }else{
                    compraRepo.delete(compra);
                    compraGuardada = null;
                }
            }
            return compraGuardada;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }



    @Override
    public List<Producto> listarMisProductos(Usuario usuarioSesion) throws Exception {
       return productoRepo.listarProductosUsuario(usuarioSesion.getCodigo());
    }

    @Override
    public Subasta agregarSubasta(Subasta subasta) throws Exception {
          return subastaRepo.save(subasta);
    }

    @Override
    public Categoria obtenerCategoria(String categoria)
    {
        return productoRepo.obtenerCategoria(categoria);
    }

    @Override
    public List<Producto> listarProductosRangoPrecio(Float precio1, Float precio2) {
        return productoRepo.listarPorRangoDePrecios(precio1,precio2);
    }

    @Override
    public List<Producto> listarProductosCiudad(String ciudad) {
        return productoRepo.listarProductosCiudad ( ciudad);
    }

    @Override
    public List<Producto> listarPorDescuento(float descuento) {
        return productoRepo.listarPorDescuento(descuento);
    }

    @Override
    public List<Producto> listarPorSeguro(int codigo) {
        return productoRepo.listarPorSeguro(codigo);
    }

    @Override
    public Number cantidadProducto(Integer codigo) {
        return productoRepo.cantidadProducto(codigo);
    }
}
