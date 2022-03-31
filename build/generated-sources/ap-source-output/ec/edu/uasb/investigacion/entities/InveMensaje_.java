package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveTipoConvocatoria;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveMensaje.class)
public class InveMensaje_ { 

    public static volatile SingularAttribute<InveMensaje, Date> menFechaCrea;
    public static volatile SingularAttribute<InveMensaje, String> menMensaje;
    public static volatile SingularAttribute<InveMensaje, Short> menCodigo;
    public static volatile SingularAttribute<InveMensaje, String> menUsuarioAct;
    public static volatile SingularAttribute<InveMensaje, String> menUsuarioCrea;
    public static volatile SingularAttribute<InveMensaje, Date> menFechaAct;
    public static volatile SingularAttribute<InveMensaje, String> menAsunto;
    public static volatile SingularAttribute<InveMensaje, InveTipoConvocatoria> inveTipoConvocatoria;

}