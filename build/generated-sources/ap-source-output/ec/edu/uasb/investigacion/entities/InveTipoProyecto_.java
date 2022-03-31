package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveTipoProyecto.class)
public class InveTipoProyecto_ { 

    public static volatile SingularAttribute<InveTipoProyecto, Short> tipCodigo;
    public static volatile CollectionAttribute<InveTipoProyecto, InveProyecto> inveProyectoCollection;
    public static volatile SingularAttribute<InveTipoProyecto, String> tipNombre;

}