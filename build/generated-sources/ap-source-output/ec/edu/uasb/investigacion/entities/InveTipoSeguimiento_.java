package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveTipoSeguimiento.class)
public class InveTipoSeguimiento_ { 

    public static volatile SingularAttribute<InveTipoSeguimiento, Short> tseOrden;
    public static volatile CollectionAttribute<InveTipoSeguimiento, InveSeguimiento> inveSeguimientoCollection;
    public static volatile SingularAttribute<InveTipoSeguimiento, String> tseNombre;
    public static volatile SingularAttribute<InveTipoSeguimiento, Short> tseCodigo;

}