package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.converter.MedioPagoConverter;
import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Scope("session")
@Component
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email, password;

    @Getter @Setter
    private Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private EmpresaMensajeriaServicio empresaMensajeriaServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;

    @Getter @Setter
    private Float subtotal;

    @Autowired
    private MedioPagoServicio medioPagoServicio;

    @Getter @Setter
    private List<MedioPago> medioPagos;

    @Getter @Setter
    private List<EmpresaMensajeria> empresaMensajerias;

    @Getter @Setter
    private Compra compra;

    @PostConstruct
    public void inicializar(){
        this.compra = new Compra();
        this.subtotal = 0F;
        this.productosCarrito = new ArrayList<>();
        medioPagos=medioPagoServicio.listarMediosPagos();
        empresaMensajerias=empresaMensajeriaServicio.listarEmpresasMensajerias();
    }

    public String iniciarSesion(){
        if( !email.isEmpty() && !password.isEmpty()){
            try {
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                return "/index?faces-redirect=true";
            } catch (Exception e) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("login-bean", fm);
            }
        }
        return null;
    }

    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public void agregarAlCarrito(Integer id, Float precio, String nombre, String imagen){
        ProductoCarrito pc = new ProductoCarrito(id, nombre, imagen, precio, 1);
        if( !productosCarrito.contains(pc) ){
            productosCarrito.add(pc);
            subtotal += precio;

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart", fm);
        }
    }

    public void eliminarDelCarrito(int indice){
        subtotal -= productosCarrito.get(indice).getPrecio();
        productosCarrito.remove(indice);
    }

    public void actualizarSubtotal(){
        subtotal = 0F;
        for (ProductoCarrito p: productosCarrito) {
            subtotal += p.getPrecio() * p.getUnidades();
        }
    }

    public void comprar(){
        if(usuarioSesion!=null && !productosCarrito.isEmpty()){
            try {
               compra.setFecha_compra( LocalDateTime.now( ZoneId.of("America/Bogota") ) );
                compra.setMiUsuario(usuarioSesion);

                //compraServicio.comprarProducto(compra);

                productoServicio.comprarProductos(productosCarrito, compra);
                compra=new Compra();
                productosCarrito.clear();
                subtotal = 0F;
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Compra realizada satisfactoriamente");
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);
            } catch (Exception e) {

                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                FacesContext.getCurrentInstance().addMessage("compra-msj", fm);

            }
        }
    }

}
