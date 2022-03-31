package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import ec.edu.uasb.principal.entities.PrinPersona;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveLector.class)
public class InveLector_ { 

    public static volatile SingularAttribute<InveLector, Date> lecFechaAct;
    public static volatile SingularAttribute<InveLector, Date> lecFechaAceptacion;
    public static volatile SingularAttribute<InveLector, PrinPersona> prinPersona;
    public static volatile SingularAttribute<InveLector, Date> lecFechaEntregaVal;
    public static volatile SingularAttribute<InveLector, String> lecAceptacion;
    public static volatile CollectionAttribute<InveLector, InveValoracion> inveValoracionCollection;
    public static volatile SingularAttribute<InveLector, Date> lecFechaLimiteAcep;
    public static volatile SingularAttribute<InveLector, Date> lecFechaCrea;
    public static volatile SingularAttribute<InveLector, Date> lecFechaDesignacion;
    public static volatile SingularAttribute<InveLector, String> lecUsuarioAct;
    public static volatile SingularAttribute<InveLector, String> lecUsuarioCrea;
    public static volatile SingularAttribute<InveLector, Long> lecCodigo;
    public static volatile SingularAttribute<InveLector, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveLector, InveRol> inveRol;
    public static volatile SingularAttribute<InveLector, Short> lecNumero;

}