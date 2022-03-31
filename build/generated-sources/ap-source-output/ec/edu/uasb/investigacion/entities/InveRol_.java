package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveEquipoMulti;
import ec.edu.uasb.investigacion.entities.InveEstadoValoracion;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveRol.class)
public class InveRol_ { 

    public static volatile SingularAttribute<InveRol, String> rolDescripcion;
    public static volatile CollectionAttribute<InveRol, InveLector> inveLectorCollection;
    public static volatile SingularAttribute<InveRol, Short> rolCodigo;
    public static volatile CollectionAttribute<InveRol, InveEquipoMulti> inveEquipoMultiCollection;
    public static volatile CollectionAttribute<InveRol, InveEstadoValoracion> inveEstadoValoracionCollection;
    public static volatile CollectionAttribute<InveRol, InveRecursoHumano> inveRecursoHumanoCollection;

}