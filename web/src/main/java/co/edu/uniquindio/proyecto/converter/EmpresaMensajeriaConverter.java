package co.edu.uniquindio.proyecto.converter;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.EmpresaMensajeria;
import co.edu.uniquindio.proyecto.servicios.EmpresaMensajeriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;

@Component
public class EmpresaMensajeriaConverter implements Converter<EmpresaMensajeria>, Serializable {

    @Autowired
    private EmpresaMensajeriaServicio empresaMensajeriaServicio;

    @Override
    public EmpresaMensajeria getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        EmpresaMensajeria empresaMensajeria = null;
        try {
            empresaMensajeria = empresaMensajeriaServicio.obtenerEmpresa(Integer.parseInt(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresaMensajeria;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, EmpresaMensajeria empresaMensajeria) {
        if(empresaMensajeria!=null){
            return empresaMensajeria.getCodigo().toString();
        }
        return "";
    }
}
