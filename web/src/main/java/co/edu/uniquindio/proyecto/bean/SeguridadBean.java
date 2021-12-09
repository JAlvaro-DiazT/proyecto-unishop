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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
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
    private DetalleCompraServicio detalleCompraServicio;

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

    @Getter @Setter
    private  ArrayList<Producto> misProductos;

    @Getter @Setter
    private  ArrayList<Compra> comprasUsuario;

    @Getter @Setter
    private ArrayList<Producto> productoSubasta;

    @Getter @Setter
    private ArrayList<DetalleCompra> detalleCompras;

    @Getter @Setter
    private Subasta subasta;

    @Value("#{param['compra']}")
    private Integer codigoCompra;

    private float subTotal;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private JavaMailSender mailSender;

    @PostConstruct
    public void inicializar(){
        this.compra = new Compra();
        this.subtotal = 0F;
        this.productosCarrito = new ArrayList<>();
        this.productoSubasta = new ArrayList<>();
        this.comprasUsuario = new ArrayList<>();
        this.detalleCompras = new ArrayList<>();
        this.subasta = new Subasta();
        medioPagos = medioPagoServicio.listarMediosPagos();
        empresaMensajerias = empresaMensajeriaServicio.listarEmpresasMensajerias();
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

                EmpresaMensajeria empresaMensajeria = compra.getMiEmpresaMensajeria();
                MedioPago medioPago = compra.getMedio_pago();
                compra.setFecha_compra( LocalDateTime.now( ZoneId.of("America/Bogota") ) );
                compra.setMiUsuario(usuarioSesion);
                compra.setMiEmpresaMensajeria(empresaMensajeria);
                compra.setMedio_pago(medioPago);


                productoServicio.comprarProductos(productosCarrito, compra);
                triggerMail();
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

    public void obtenerMisProductos() {

        if(usuarioSesion!=null) {
            try {
                this.misProductos= (ArrayList<Producto>) productoServicio.listarMisProductos(usuarioSesion);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void subastar(Producto miProducto){

        if( !productoSubasta.contains(miProducto) ){

            try {
                productoSubasta.add(miProducto);
                subasta.setFecha_limite(LocalDateTime.now().plusMonths(1));
                subasta.setMiProducto(miProducto);
                productoServicio.agregarSubasta(subasta);
            } catch (Exception e) {
                e.printStackTrace();
            }

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto subastado con exito");
            FacesContext.getCurrentInstance().addMessage("subastarMensaje", fm);
        } else {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "El producto ya esta en subasta");
            FacesContext.getCurrentInstance().addMessage("subastarMensaje", fm);
        }
    }

    public void obtenerComprasUsuario(){
        if(usuarioSesion!=null){
            try{
                this.comprasUsuario = (ArrayList<Compra>) compraServicio.listarComprasUsuario(usuarioSesion.getCodigo());
                comprasUsuario.forEach(System.out::println);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public String mostrarDetalleCompra(String codigo) {
        this.codigoCompra=Integer.parseInt(codigo);
        return "detalleCompra?faces-redirect=true&amp;compra="+codigo;
    }

    public void listarDetalleCompra(){
        if(usuarioSesion!=null){
            try{
                this.detalleCompras = (ArrayList<DetalleCompra>) detalleCompraServicio.listarDetallesCompra(codigoCompra);
                comprasUsuario.forEach(System.out::println);
                calcularSubTotal();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void calcularSubTotal(){
        subTotal=0F;
        for (DetalleCompra detalle:detalleCompras) {
            subTotal+=detalle.getUnidades()*detalle.getPrecio_producto();
        }
    }

    public void triggerMail(){

        String mensaje = "<h1>UNISHOP</h1>";

        mensaje += "<h2>Hola, " + usuarioSesion.getNombre() + "</h2>"
                + "\n\nTu pedido ha sido confirmado, llegará en los próximos días.\n"
                + "\n<h4>DETALLES DE LA COMPRA</h4>"
                + "<P>" + productosCompra() + "</P>"
                + "</br>SubTotal: $" + subTotal
                + "<h2>Total compra: $" + subTotal
                + "</h2></br></br>Atentamente, "
                + "<h3>UNISHOP</h3>";
        try {
            emailSenderService.sendSimpleEmail("carolinitatorres329@gmail.com", mensaje,
                    "Compra Unishop");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String productosCompra(){
        String mensaje="";
        for(int i=0; i<productosCarrito.size();i++){
            mensaje=mensaje.concat(productosCarrito.get(i).getNombre())+"     ";
            mensaje=mensaje.concat(String.valueOf(productosCarrito.get(i).getPrecio()))+"    ";
            mensaje.concat("\\t");
        }
        return mensaje;
    }
}
