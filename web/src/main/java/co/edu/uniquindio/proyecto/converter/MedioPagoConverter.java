package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.MedioPago;
import co.edu.uniquindio.proyecto.servicios.MedioPagoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class MedioPagoConverter implements Converter<MedioPago>, Serializable {
    @Autowired
    private MedioPagoServicio medioPagoServicio;


    @Override
    public MedioPago getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        MedioPago medioPago = null;
        try {
            medioPago = medioPagoServicio.obtenerMedioPago(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medioPago;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, MedioPago medioPago) {
        if(medioPago!=null){
            return medioPago.getCodigo().toString();
        }
        return "";
    }
}
