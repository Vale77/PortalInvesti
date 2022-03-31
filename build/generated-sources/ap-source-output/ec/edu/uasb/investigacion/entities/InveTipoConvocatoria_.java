package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveMensaje;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveTipoConvocatoria.class)
public class InveTipoConvocatoria_ { 

    public static volatile SingularAttribute<InveTipoConvocatoria, Short> tcvCodigo;
    public static volatile CollectionAttribute<InveTipoConvocatoria, InveMensaje> inveMensajeCollection;
    public static volatile SingularAttribute<InveTipoConvocatoria, String> tcvNombre;
    public static volatile CollectionAttribute<InveTipoConvocatoria, InveConvocatoria> inveConvocatoriaCollection;

}