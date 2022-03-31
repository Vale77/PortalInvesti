package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveRol;
import ec.edu.uasb.investigacion.entities.InveValoracion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveEstadoValoracion.class)
public class InveEstadoValoracion_ { 

    public static volatile SingularAttribute<InveEstadoValoracion, String> esvDescripcion;
    public static volatile SingularAttribute<InveEstadoValoracion, String> esvCodigo;
    public static volatile SingularAttribute<InveEstadoValoracion, String> esvEstado;
    public static volatile CollectionAttribute<InveEstadoValoracion, InveValoracion> inveValoracionCollection;
    public static volatile SingularAttribute<InveEstadoValoracion, InveRol> inveRol;

}