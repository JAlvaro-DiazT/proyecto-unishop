package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Autowired
    private SeguroServicio seguroServicio;

    private ArrayList<String> imagenes;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Seguro> seguros;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Value("${upload.url}")
    private String urlUploads;

    @PostConstruct
    public void iniciarProducto(){

        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
        categorias = categoriaServicio.listarCategorias();
        ciudades = ciudadServicio.listarCiudades();
        seguros = seguroServicio.listarSeguros();
    }

    public void crearProducto(){
        try {
            if(usuarioSesion!=null){
                if(!imagenes.isEmpty()){
                    producto.setVendedor(usuarioSesion);
                    producto.setImagen(imagenes);
                    producto.setFecha_limite( LocalDateTime.now().plusMonths(2) );
                    productoServicio.publicarProducto(producto);

                    FacesMessage Msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto creado satisfactoriamente");
                    FacesContext.getCurrentInstance().addMessage("msj-bean", Msg);
                }else{
                    FacesMessage Msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Es necesario subir al menos una imagen");
                    FacesContext.getCurrentInstance().addMessage("msj-bean", Msg);
                }
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", fm);
        }
    }

    public void subirImagenes(FileUploadEvent event){

        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);

        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen (UploadedFile imagen){
        try {
            File archivo = new File(urlUploads+"/"+imagen.getFileName());
            OutputStream ou = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), ou);
            return imagen.getFileName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
