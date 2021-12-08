package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
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

}
