package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;



    @GetMapping
    public List<Producto> listar(){
        return productoServicio.listarTodosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") String id){
        try{
            Integer idProducto = Integer.parseInt(id);
            Producto producto = productoServicio.obtenerProducto(idProducto);
            return ResponseEntity.status(200).body(producto);
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Producto producto) {
        try{
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se registro correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Producto producto) {
        try{
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se actualizo correctamente"));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") String id) {
        try{
            Integer idProducto = Integer.parseInt(id);
            productoServicio.eliminarProducto(idProducto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se elimino correctamente"));
        } catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }
    @GetMapping("/categoria/{cat}")
    public ResponseEntity<?> categoria(@PathVariable("cat") String cat) {
        try{

            Categoria categoria = productoServicio.obtenerCategoria(cat);
            Integer codCategoria = categoria.getCodigo();
            List<Producto> productos = productoServicio.listarTodosProductos();
            List<Producto> listaProduc = null;

            for (int i=0; i<productos.size(); i++){
                Producto producto = productos.get(i);
                List<Categoria> categorias = producto.getCategoria();

                for (int j=0; j<categorias.size(); j++){
                    Categoria categor = categorias.get(j);
                    int codCat = categor.getCodigo();

                    if(codCat == codCategoria){
                        listaProduc.add(producto);
                    }
                }
            }

            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje("Categoria no encontrada"));
        }
    }


    //Buscar por medio de rango de precio.
    @GetMapping("/precio/{precio1}/{precio2}")
    public ResponseEntity<?> rangoPrecio(@PathVariable("precio1") Float precio1, @PathVariable("precio2")Float precio2) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductosRangoPrecio(precio1, precio2);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Buscar por medio de lugar.
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<?> buscarPorCiudad(@PathVariable("ciudad") String ciudad) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductosCiudad(ciudad);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    //------------------------------------------------------------------------


    //Buscar por descuento.
    @GetMapping("/descuento/{descuento}")
    public ResponseEntity<?> buscarPorDescuento(@PathVariable("descuento") float descuento) {
        try{
            List<Producto> listaProduc = productoServicio.listarPorDescuento(descuento);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Buscar por descuento.
    @GetMapping("/seguro/{seguro}")
    public ResponseEntity<?> buscarPorSeguro(@PathVariable("seguro") int codigo) {
        try{
            List<Producto> listaProduc = productoServicio.listarPorSeguro(codigo);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
}
